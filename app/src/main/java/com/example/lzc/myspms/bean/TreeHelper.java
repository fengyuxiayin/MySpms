package com.example.lzc.myspms.bean;

import com.example.lzc.myspms.R;
import com.example.lzc.myspms.bean.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/lmj623565791/article/details/40212367
 * @author zhy
 *
 */
public class TreeHelper
{
	/**
	 * 传入我们的普通bean，转化为我们排序后的Node
	 * 
	 * @param datas
	 * @param defaultExpandLevel
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> List<com.example.lzc.myspms.bean.Node> getSortedNodes(List<T> datas,
																  int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException

	{
		List<com.example.lzc.myspms.bean.Node> result = new ArrayList<com.example.lzc.myspms.bean.Node>();
		// 将用户数据转化为List<Node>
		List<com.example.lzc.myspms.bean.Node> nodes = convetData2Node(datas);
		// 拿到根节点
		List<com.example.lzc.myspms.bean.Node> rootNodes = getRootNodes(nodes);
		// 排序以及设置Node间关系
		for (com.example.lzc.myspms.bean.Node node : rootNodes)
		{
			addNode(result, node, defaultExpandLevel, 1);
		}
		return result;
	}

	/**
	 * 过滤出所有可见的Node
	 * 
	 * @param nodes
	 * @return
	 */
	public static List<com.example.lzc.myspms.bean.Node> filterVisibleNode(List<com.example.lzc.myspms.bean.Node> nodes)
	{
		List<com.example.lzc.myspms.bean.Node> result = new ArrayList<com.example.lzc.myspms.bean.Node>();

		for (com.example.lzc.myspms.bean.Node node : nodes)
		{
			// 如果为跟节点，或者上层目录为展开状态
			if (node.isRoot() || node.isParentExpand())
			{
				setNodeIcon(node);
				result.add(node);
			}
		}
		return result;
	}

	/**
	 * 将我们的数据转化为树的节点
	 * 
	 * @param datas
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private static <T> List<com.example.lzc.myspms.bean.Node> convetData2Node(List<T> datas)
			throws IllegalArgumentException, IllegalAccessException

	{
		List<com.example.lzc.myspms.bean.Node> nodes = new ArrayList<com.example.lzc.myspms.bean.Node>();
		com.example.lzc.myspms.bean.Node node = null;

		for (T t : datas)
		{
			int id = -1;
			int pId = -1;
			String label = null;
			Class<? extends Object> clazz = t.getClass();
			Field[] declaredFields = clazz.getDeclaredFields();
			for (Field f : declaredFields)
			{
				if (f.getAnnotation(TreeNodeId.class) != null)
				{
					f.setAccessible(true);
					id = f.getInt(t);
				}
				if (f.getAnnotation(TreeNodePid.class) != null)
				{
					f.setAccessible(true);
					pId = f.getInt(t);
				}
				if (f.getAnnotation(TreeNodeLabel.class) != null)
				{
					f.setAccessible(true);
					label = (String) f.get(t);
				}
				if (id != -1 && pId != -1 && label != null)
				{
					break;
				}
			}
			node = new com.example.lzc.myspms.bean.Node(id, pId, label);
			nodes.add(node);
		}

		/**
		 * 设置Node间，父子关系;让每两个节点都比较一次，即可设置其中的关系
		 */
		for (int i = 0; i < nodes.size(); i++)
		{
			com.example.lzc.myspms.bean.Node n = nodes.get(i);
			for (int j = i + 1; j < nodes.size(); j++)
			{
				com.example.lzc.myspms.bean.Node m = nodes.get(j);
				if (m.getpId() == n.getId())
				{
					n.getChildren().add(m);
					m.setParent(n);
				} else if (m.getId() == n.getpId())
				{
					m.getChildren().add(n);
					n.setParent(m);
				}
			}
		}

		// 设置图片
		for (com.example.lzc.myspms.bean.Node n : nodes)
		{
			setNodeIcon(n);
		}
		return nodes;
	}

	private static List<com.example.lzc.myspms.bean.Node> getRootNodes(List<com.example.lzc.myspms.bean.Node> nodes)
	{
		List<com.example.lzc.myspms.bean.Node> root = new ArrayList<com.example.lzc.myspms.bean.Node>();
		for (com.example.lzc.myspms.bean.Node node : nodes)
		{
			if (node.isRoot())
				root.add(node);
		}
		return root;
	}

	/**
	 * 把一个节点上的所有的内容都挂上去
	 */
	private static void addNode(List<com.example.lzc.myspms.bean.Node> nodes, com.example.lzc.myspms.bean.Node node,
								int defaultExpandLeval, int currentLevel)
	{

		nodes.add(node);
		if (defaultExpandLeval >= currentLevel)
		{
			node.setExpand(true);
		}

		if (node.isLeaf())
			return;
		for (int i = 0; i < node.getChildren().size(); i++)
		{
			addNode(nodes, node.getChildren().get(i), defaultExpandLeval,
					currentLevel + 1);
		}
	}

	/**
	 * 设置节点的图标
	 * 
	 * @param node
	 */
	private static void setNodeIcon(com.example.lzc.myspms.bean.Node node)
	{
		if (node.getChildren().size() > 0 && node.isExpand())
		{
			node.setIcon(R.mipmap.shangla);
		} else if (node.getChildren().size() > 0 && !node.isExpand())
		{
			node.setIcon(R.mipmap.xiala);
		} else
			node.setIcon(-1);

	}

}
