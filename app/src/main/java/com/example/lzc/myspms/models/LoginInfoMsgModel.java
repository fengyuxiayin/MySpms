package com.example.lzc.myspms.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZC on 2017/10/31.
 */
public class LoginInfoMsgModel implements Serializable {

    /**
     * account : {"createBy":1,"createTime":1501480099000,"id":1,"loginName":"admin","loginType":2,"modifyBy":1,"modifyTime":1520910653000,"roleId":1,"ssId":1,"status":1}
     * componentList : [{"createBy":1,"createTime":1510133835000,"editable":1,"id":273,"modifyTime":1510133835000,"num":10,"parentId":3,"resourceLevel":3,"resourceName":"系统消息查询","resourceType":2,"resourceUrl":"/sysMessage/find","status":1},{"createBy":1,"createTime":1510133882000,"editable":1,"id":274,"modifyTime":1510133882000,"num":20,"parentId":3,"resourceLevel":3,"resourceName":"查询单条消息","resourceType":2,"resourceUrl":"/sysMessage/findById","status":1},{"createBy":1,"createTime":1510133924000,"editable":1,"id":275,"modifyTime":1510133924000,"num":30,"parentId":3,"resourceLevel":3,"resourceName":"发送消息","resourceType":2,"resourceUrl":"/sysMessage/sendMsg","status":1},{"createBy":1,"createTime":1510133960000,"editable":1,"id":276,"modifyTime":1510133960000,"num":40,"parentId":3,"resourceLevel":3,"resourceName":"获取未读消息数量","resourceType":2,"resourceUrl":"/sysMessage/unreadCount","status":1},{"createBy":1,"createTime":1510133985000,"editable":1,"id":277,"modifyTime":1510133985000,"num":50,"parentId":3,"resourceLevel":3,"resourceName":"标记已读","resourceType":2,"resourceUrl":"/sysMessage/markRead","status":1},{"createBy":1,"createTime":1510305103000,"editable":1,"id":285,"modifyTime":1510305103000,"num":60,"parentId":3,"resourceLevel":3,"resourceName":"app消息推送","resourceType":2,"resourceUrl":"/jpush/sendToAllAndroid","status":1},{"createBy":1,"createTime":1510538189000,"editable":1,"id":289,"modifyBy":1,"modifyTime":1510538399000,"num":70,"parentId":3,"resourceLevel":3,"resourceName":"精确查询","resourceType":2,"resourceUrl":"/project/findAll","status":1},{"createBy":1,"createTime":1512961161000,"editable":1,"id":316,"modifyTime":1512961161000,"num":80,"parentId":3,"resourceLevel":3,"resourceName":"获取人员坐标点","resourceType":2,"resourceUrl":"/baseStaffPolyline/findByStaff","status":1},{"createBy":1,"createTime":1527493761000,"editable":1,"id":364,"modifyTime":1527493761000,"num":90,"parentId":3,"resourceLevel":3,"resourceName":"所有账号find","resourceType":2,"resourceUrl":"/authAccount/findAll","status":1},{"createBy":1,"createTime":1527500910000,"editable":1,"id":365,"modifyTime":1527500910000,"num":100,"parentId":3,"resourceLevel":3,"resourceName":"消息findById","resourceType":2,"resourceUrl":"/sysMessage/findById","status":1},{"createBy":1,"createTime":1505182126000,"id":158,"modifyTime":1505182126000,"num":10,"parentId":1,"resourceLevel":2,"resourceName":"框架","resourceType":2,"resourceUrl":"/frame","status":1},{"createBy":1,"createTime":1509417605000,"editable":1,"id":234,"modifyTime":1509417605000,"num":10,"parentId":233,"resourceLevel":4,"resourceName":"新增片区","resourceType":2,"resourceUrl":"/baseArea/save","status":1},{"createBy":1,"createTime":1502433646000,"editable":1,"id":37,"modifyBy":1,"modifyTime":1502439818000,"num":10,"parentId":31,"resourceLevel":4,"resourceName":"查询社区","resourceType":2,"resourceUrl":"/community/find","status":1},{"createBy":1,"createTime":1502433719000,"editable":1,"id":38,"modifyBy":1,"modifyTime":1502439832000,"num":20,"parentId":31,"resourceLevel":4,"resourceName":"根据ID查询社区","resourceType":2,"resourceUrl":"/community/findById","status":1},{"createBy":1,"createTime":1502433797000,"editable":1,"id":39,"modifyBy":1,"modifyTime":1502439877000,"num":30,"parentId":31,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/community/save","status":1},{"createBy":1,"createTime":1502433864000,"editable":1,"id":40,"modifyBy":1,"modifyTime":1502439922000,"num":40,"parentId":31,"resourceLevel":4,"resourceName":"删除","resourceType":2,"resourceUrl":"/community/delete","status":1},{"createBy":1,"createTime":1502433978000,"editable":1,"id":41,"modifyBy":1,"modifyTime":1502439938000,"num":50,"parentId":31,"resourceLevel":4,"resourceName":"不分页查询所有社区","resourceType":2,"resourceUrl":"/community/findAll","status":1},{"createBy":1,"createTime":1502440359000,"editable":1,"id":62,"modifyTime":1502440359000,"num":10,"parentId":61,"resourceLevel":5,"resourceName":"查询坐标数据集合","resourceType":2,"resourceUrl":"/baseCommunityPolygon/find","status":1},{"createBy":1,"createTime":1502440436000,"editable":1,"id":63,"modifyTime":1502440436000,"num":20,"parentId":61,"resourceLevel":5,"resourceName":"保存多边形坐标点","resourceType":2,"resourceUrl":"/baseCommunityPolygon/saveCoordinates","status":1},{"createBy":1,"createTime":1502443546000,"editable":1,"id":70,"modifyBy":1,"modifyTime":1526952380000,"num":10,"parentId":69,"resourceLevel":4,"resourceName":"查询","resourceType":2,"resourceUrl":"/team/find","status":1},{"createBy":1,"createTime":1502443546000,"editable":1,"id":71,"modifyBy":1,"modifyTime":1526952615000,"num":20,"parentId":69,"resourceLevel":4,"resourceName":"根据id查询","resourceType":2,"resourceUrl":"/team/findById","status":1},{"createBy":1,"createTime":1502443546000,"editable":1,"id":72,"modifyBy":1,"modifyTime":1526952633000,"num":30,"parentId":69,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/team/save","status":1},{"createBy":1,"createTime":1502443546000,"editable":1,"id":73,"modifyBy":1,"modifyTime":1526952650000,"num":40,"parentId":69,"resourceLevel":4,"resourceName":"删除","resourceType":2,"resourceUrl":"/team/delete","status":1},{"createBy":1,"createTime":1502443546000,"editable":1,"id":74,"modifyBy":1,"modifyTime":1526952669000,"num":50,"parentId":69,"resourceLevel":4,"resourceName":"查询全部小组不分页","resourceType":2,"resourceUrl":"/team/findALL","status":1},{"createBy":1,"createTime":1526917100000,"editable":1,"id":356,"modifyTime":1526917100000,"num":60,"parentId":69,"resourceLevel":4,"resourceName":"查询全部人员","resourceType":2,"resourceUrl":"/baseStaff/findAll","status":1},{"createBy":1,"createTime":1526969578000,"editable":1,"id":359,"modifyTime":1526969578000,"num":80,"parentId":69,"resourceLevel":4,"resourceName":"检查记录","resourceType":2,"resourceUrl":"/checkInfo/find","status":1},{"createBy":1,"createTime":1526969635000,"editable":1,"id":360,"modifyTime":1526969635000,"num":90,"parentId":69,"resourceLevel":4,"resourceName":"检查详情","resourceType":2,"resourceUrl":"/checkProject/find","status":1},{"createBy":1,"createTime":1502432929000,"editable":1,"id":33,"modifyBy":1,"modifyTime":1502438614000,"num":10,"parentId":30,"resourceLevel":4,"resourceName":"查找企业","resourceType":2,"resourceUrl":"/baseEnterprise/find","status":1},{"createBy":1,"createTime":1502433051000,"editable":1,"id":34,"modifyBy":1,"modifyTime":1502438627000,"num":20,"parentId":30,"resourceLevel":4,"resourceName":"根据ID查询","resourceType":2,"resourceUrl":"/baseEnterprise/findById","status":1},{"createBy":1,"createTime":1502433110000,"editable":1,"id":35,"modifyBy":1,"modifyTime":1502438641000,"num":30,"parentId":30,"resourceLevel":4,"resourceName":"保存企业","resourceType":2,"resourceUrl":"/baseEnterprise/save","status":1},{"createBy":1,"createTime":1502433179000,"editable":1,"id":36,"modifyBy":1,"modifyTime":1502438660000,"num":49,"parentId":30,"resourceLevel":4,"resourceName":"删除企业","resourceType":2,"resourceUrl":"/baseEnterprise/delete","status":1},{"createBy":1,"createTime":1527045618000,"editable":1,"id":363,"modifyTime":1527045618000,"num":80,"parentId":30,"resourceLevel":4,"resourceName":"findbyid带人员信息","resourceType":2,"resourceUrl":"/baseEnterprise/findByIdWithStaff","status":1},{"createBy":1,"createTime":1512466107000,"editable":1,"id":315,"modifyTime":1512466107000,"num":80,"parentId":30,"resourceLevel":4,"resourceName":"是否在区域范围内","resourceType":2,"resourceUrl":"/baseEnterprise/isInArea","status":1},{"createBy":1,"createTime":1502437235000,"editable":1,"id":54,"modifyBy":1,"modifyTime":1502439414000,"num":90,"parentId":30,"resourceLevel":4,"resourceName":"查询公司项目","resourceType":2,"resourceUrl":"/project/find","status":1},{"createBy":1,"createTime":1502437329000,"editable":1,"id":55,"modifyBy":1,"modifyTime":1502439437000,"num":100,"parentId":30,"resourceLevel":4,"resourceName":"根据id查询项目","resourceType":2,"resourceUrl":"/project/findById","status":1},{"createBy":1,"createTime":1502437453000,"editable":1,"id":56,"modifyBy":1,"modifyTime":1502439459000,"num":110,"parentId":30,"resourceLevel":4,"resourceName":"项目保存","resourceType":2,"resourceUrl":"/project/save","status":1},{"createBy":1,"createTime":1502437545000,"editable":1,"id":57,"modifyBy":1,"modifyTime":1502439484000,"num":120,"parentId":30,"resourceLevel":4,"resourceName":"项目删除","resourceType":2,"resourceUrl":"/project/delete","status":1},{"createBy":1,"createTime":1502437714000,"editable":1,"id":58,"modifyBy":1,"modifyTime":1502444414000,"num":130,"parentId":30,"resourceLevel":4,"resourceName":"企业相关账号信息查询","resourceType":2,"resourceUrl":"/authAccount/find","status":1},{"createBy":1,"createTime":1509350105000,"editable":1,"id":227,"modifyTime":1509350105000,"num":140,"parentId":30,"resourceLevel":4,"resourceName":"检查项目信息到处","resourceType":2,"resourceUrl":"/project/exportProject","status":1},{"createBy":1,"createTime":1509352892000,"editable":1,"id":231,"modifyTime":1509352892000,"num":150,"parentId":30,"resourceLevel":4,"resourceName":"企业信息导出","resourceType":2,"resourceUrl":"/baseEnterprise/export","status":1},{"createBy":1,"createTime":1509428987000,"editable":1,"id":239,"modifyTime":1509428987000,"num":160,"parentId":30,"resourceLevel":4,"resourceName":"判断是否在片区内","resourceType":2,"resourceUrl":"/baseEnterprise/isInArea","status":1},{"createBy":1,"createTime":1509953622000,"editable":1,"id":266,"modifyTime":1509953622000,"num":170,"parentId":30,"resourceLevel":4,"resourceName":"生成项目二维码","resourceType":2,"resourceUrl":"/project/genQRCode","status":1},{"createBy":1,"createTime":1510019311000,"editable":1,"id":270,"modifyTime":1510019311000,"num":180,"parentId":30,"resourceLevel":4,"resourceName":"企业检查记录","resourceType":2,"resourceUrl":"/baseEnterprise/queryCheckRecords","status":1},{"createBy":1,"createTime":1510882351000,"editable":1,"id":297,"modifyTime":1510882351000,"num":190,"parentId":30,"resourceLevel":4,"resourceName":"获取条码","resourceType":2,"resourceUrl":"/project/getQRCodeList","status":1},{"createBy":1,"createTime":1502434495000,"editable":1,"id":42,"modifyTime":1502434495000,"num":10,"parentId":32,"resourceLevel":4,"resourceName":"人员查询","resourceType":2,"resourceUrl":"/baseStaff/find","status":1},{"createBy":1,"createTime":1502434553000,"editable":1,"id":43,"modifyTime":1502434553000,"num":20,"parentId":32,"resourceLevel":4,"resourceName":"根据id查询","resourceType":2,"resourceUrl":"/baseStaff/findById","status":1},{"createBy":1,"createTime":1502434646000,"editable":1,"id":44,"modifyTime":1502434646000,"num":30,"parentId":32,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/baseStaff/save","status":1},{"createBy":1,"createTime":1502434718000,"editable":1,"id":45,"modifyTime":1502434718000,"num":40,"parentId":32,"resourceLevel":4,"resourceName":"删除","resourceType":2,"resourceUrl":"/baseStaff/delete","status":1},{"createBy":1,"createTime":1502434894000,"editable":1,"id":46,"modifyTime":1502434894000,"num":50,"parentId":32,"resourceLevel":4,"resourceName":"查询人员所属单位","resourceType":2,"resourceUrl":"/baseStaff/findSsdw","status":1},{"createBy":1,"createTime":1502442114000,"editable":1,"id":64,"modifyTime":1502442114000,"num":60,"parentId":32,"resourceLevel":4,"resourceName":"根据社区查找人员","resourceType":2,"resourceUrl":"/baseStaff/findStaff","status":1},{"createBy":1,"createTime":1504599299000,"editable":1,"id":116,"modifyBy":1,"modifyTime":1504599384000,"num":70,"parentId":32,"resourceLevel":4,"resourceName":"人员详情页","resourceType":2,"resourceUrl":"/basePage/baseStaffDetail","status":1},{"createBy":1,"createTime":1526958483000,"editable":1,"id":358,"modifyTime":1526958483000,"num":80,"parentId":32,"resourceLevel":4,"resourceName":"小组或社区查找所有人员","resourceType":2,"resourceUrl":"/baseStaff/findAllStaffForTeamOrCommunity","status":1},{"createBy":1,"createTime":1527577957000,"editable":1,"id":366,"modifyTime":1527577957000,"num":90,"parentId":32,"resourceLevel":4,"resourceName":"首页人员查询","resourceType":2,"resourceUrl":"/baseStaff/findForHomePage","status":1},{"createBy":1,"createTime":1525942159000,"editable":1,"id":330,"modifyTime":1525942159000,"num":10,"parentId":328,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/baseSafetyStandard/save","status":1},{"createBy":1,"createTime":1525942194000,"editable":1,"id":331,"modifyTime":1525942194000,"num":20,"parentId":328,"resourceLevel":4,"resourceName":"单条查询","resourceType":2,"resourceUrl":"/baseSafetyStandard/findById","status":1},{"createBy":1,"createTime":1525942729000,"editable":1,"id":332,"modifyTime":1525942729000,"num":30,"parentId":328,"resourceLevel":4,"resourceName":"查询树结构","resourceType":2,"resourceUrl":"/baseSafetyStandard/getTreeGrid","status":1},{"createBy":1,"createTime":1524209458000,"editable":1,"id":318,"modifyBy":1,"modifyTime":1524557040000,"num":10,"parentId":317,"resourceLevel":4,"resourceName":"根据id查询","resourceType":2,"resourceUrl":"/baseDepartment/findById","status":1},{"createBy":1,"createTime":1524209524000,"editable":1,"id":319,"modifyTime":1524209524000,"num":20,"parentId":317,"resourceLevel":4,"resourceName":"查询","resourceType":2,"resourceUrl":"/baseDepartment/find","status":1},{"createBy":1,"createTime":1524209632000,"editable":1,"id":321,"modifyTime":1524209632000,"num":40,"parentId":317,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/baseDepartment/save","status":1},{"createBy":1,"createTime":1524215781000,"editable":1,"id":325,"modifyTime":1524215781000,"num":50,"parentId":317,"resourceLevel":4,"resourceName":"成员单位数列表","resourceType":2,"resourceUrl":"/baseDepartment/getTreeGrid","status":1},{"createBy":1,"createTime":1524557017000,"editable":1,"id":327,"modifyTime":1524557017000,"num":60,"parentId":317,"resourceLevel":4,"resourceName":"查询所有成员单位","resourceType":2,"resourceUrl":"/baseDepartment/findAll","status":1},{"createBy":1,"createTime":1500976696000,"editable":1,"id":13,"modifyBy":1,"modifyTime":1500976707000,"num":10,"parentId":12,"resourceLevel":4,"resourceName":"查询树列表","resourceType":2,"resourceUrl":"/authResource/getTreeGrid","status":1},{"createBy":1,"createTime":1500976765000,"editable":1,"id":14,"modifyTime":1500976765000,"num":20,"parentId":12,"resourceLevel":4,"resourceName":"查询树结构","resourceType":2,"resourceUrl":"/authResource/getTree","status":1},{"createBy":1,"createTime":1500976792000,"editable":1,"id":15,"modifyTime":1500976792000,"num":30,"parentId":12,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/authResource/saveEdit","status":1},{"createBy":1,"createTime":1500976848000,"editable":1,"id":16,"modifyTime":1500976848000,"num":40,"parentId":12,"resourceLevel":4,"resourceName":"删除","resourceType":2,"resourceUrl":"/authResource/delete","status":1},{"createBy":1,"createTime":1500976895000,"editable":1,"id":17,"modifyTime":1500976895000,"num":50,"parentId":12,"resourceLevel":4,"resourceName":"根据id查询","resourceType":2,"resourceUrl":"/authResource/findById","status":1},{"createBy":1,"createTime":1500977607000,"editable":1,"id":19,"modifyTime":1500977607000,"num":10,"parentId":18,"resourceLevel":4,"resourceName":"查询角色","resourceType":2,"resourceUrl":"/role/find","status":1},{"createBy":1,"createTime":1500977650000,"editable":1,"id":20,"modifyTime":1500977650000,"num":20,"parentId":18,"resourceLevel":4,"resourceName":"根据id查询","resourceType":2,"resourceUrl":"/role/findById","status":1},{"createBy":1,"createTime":1500977672000,"editable":1,"id":21,"modifyTime":1500977672000,"num":30,"parentId":18,"resourceLevel":4,"resourceName":"保存或修改角色信息","resourceType":2,"resourceUrl":"/role/save","status":1},{"createBy":1,"createTime":1500977693000,"editable":1,"id":22,"modifyTime":1500977693000,"num":40,"parentId":18,"resourceLevel":4,"resourceName":"删除角色","resourceType":2,"resourceUrl":"/role/delete","status":1},{"createBy":1,"createTime":1510902298000,"editable":1,"id":301,"modifyTime":1510902298000,"num":50,"parentId":18,"resourceLevel":4,"resourceName":"查找所有角色不分页","resourceType":2,"resourceUrl":"/role/findAll","status":1},{"createBy":1,"createTime":1503306683000,"editable":1,"id":80,"modifyBy":1,"modifyTime":1510108909000,"num":10,"parentId":79,"resourceLevel":4,"resourceName":"查询","resourceType":2,"resourceUrl":"/jobProcess/findJobProcess","status":1},{"createBy":1,"createTime":1510108733000,"editable":1,"id":272,"modifyTime":1510108733000,"num":20,"parentId":79,"resourceLevel":4,"resourceName":"查看任务详情","resourceType":2,"resourceUrl":"/jobProcess/findProject","status":1},{"createBy":1,"createTime":1509957581000,"editable":1,"id":267,"modifyTime":1509957581000,"num":50,"parentId":75,"resourceLevel":4,"resourceName":"检查发布状态","resourceType":2,"resourceUrl":"/jobCycle/checkStatus","status":1},{"createBy":1,"createTime":1510278108000,"editable":1,"id":278,"modifyBy":1,"modifyTime":1510297246000,"num":60,"parentId":75,"resourceLevel":4,"resourceName":"单条发布任务","resourceType":2,"resourceUrl":"/jobCycle/publishUnique","status":1},{"createBy":1,"createTime":1510297231000,"editable":1,"id":284,"modifyTime":1510297231000,"num":70,"parentId":75,"resourceLevel":4,"resourceName":"批量发布任务","resourceType":2,"resourceUrl":"/jobCycle/publish","status":1},{"createBy":1,"createTime":1526784215000,"editable":1,"id":353,"modifyTime":1526784215000,"num":20,"parentId":351,"resourceLevel":4,"resourceName":"任务调度","resourceType":2,"resourceUrl":"/jobCycleRecord/changeTeam","status":1},{"createBy":1,"createTime":1526889783000,"editable":1,"id":355,"modifyTime":1526889783000,"num":20,"parentId":351,"resourceLevel":4,"resourceName":"任务查询","resourceType":2,"resourceUrl":"/jobCycleRecord/findWithoutJcdwlx","status":1},{"createBy":1,"createTime":1502442659000,"editable":1,"id":65,"modifyTime":1502442659000,"num":11,"parentId":48,"resourceLevel":4,"resourceName":"多维度查询","resourceType":2,"resourceUrl":"/baseEnterprise/find","status":1},{"createBy":1,"createTime":1502442712000,"editable":1,"id":66,"modifyTime":1502442712000,"num":12,"parentId":48,"resourceLevel":4,"resourceName":"根据id查询","resourceType":2,"resourceUrl":"/baseEnterprise/findById","status":1},{"createBy":1,"createTime":1502442794000,"editable":1,"id":67,"modifyTime":1502442794000,"num":13,"parentId":48,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/baseEnterprise/save","status":1},{"createBy":1,"createTime":1509960348000,"editable":1,"id":268,"modifyTime":1509960348000,"num":15,"parentId":48,"resourceLevel":4,"resourceName":"企业信息导出","resourceType":2,"resourceUrl":"/baseEnterprise/export","status":1},{"createBy":1,"createTime":1505464785000,"editable":1,"id":160,"modifyTime":1505464785000,"num":10,"parentId":159,"resourceLevel":4,"resourceName":"项目多维度查询","resourceType":2,"resourceUrl":"/project/query","status":1},{"createBy":1,"createTime":1510885142000,"editable":1,"id":298,"modifyTime":1510885142000,"num":10,"parentId":205,"resourceLevel":4,"resourceName":"企业统计","resourceType":2,"resourceUrl":"/baseEnterprise/findAndCount","status":1},{"createBy":1,"createTime":1509551817000,"editable":1,"id":259,"modifyBy":1,"modifyTime":1509673028000,"num":30,"parentId":207,"resourceLevel":4,"resourceName":"群发","resourceType":2,"resourceUrl":"/weChatMessage/messSend","status":1},{"createBy":1,"createTime":1509551860000,"editable":1,"id":260,"modifyTime":1509551860000,"num":40,"parentId":207,"resourceLevel":4,"resourceName":"获取标签","resourceType":2,"resourceUrl":"/weChatTags/find","status":1},{"createBy":1,"createTime":1509350575000,"editable":1,"id":228,"modifyTime":1509350575000,"num":10,"parentId":226,"resourceLevel":4,"resourceName":"标签查询","resourceType":2,"resourceUrl":"/weChatTags/find","status":1},{"createBy":1,"createTime":1509351603000,"editable":1,"id":229,"modifyTime":1509351603000,"num":20,"parentId":226,"resourceLevel":4,"resourceName":"保存标签","resourceType":2,"resourceUrl":"/weChatTags/save","status":1},{"createBy":1,"createTime":1509351637000,"editable":1,"id":230,"modifyTime":1509351637000,"num":30,"parentId":226,"resourceLevel":4,"resourceName":"删除标签","resourceType":2,"resourceUrl":"/weChatTags/delete","status":1},{"createBy":1,"createTime":1509499916000,"editable":1,"id":241,"modifyTime":1509499916000,"num":10,"parentId":240,"resourceLevel":4,"resourceName":"菜单查询","resourceType":2,"resourceUrl":"/weChatMenu/find","status":1},{"createBy":1,"createTime":1509499949000,"editable":1,"id":242,"modifyTime":1509499949000,"num":20,"parentId":240,"resourceLevel":4,"resourceName":"菜单保存","resourceType":2,"resourceUrl":"/weChatMenu/save","status":1},{"createBy":1,"createTime":1509499987000,"editable":1,"id":243,"modifyTime":1509499987000,"num":30,"parentId":240,"resourceLevel":4,"resourceName":"菜单删除","resourceType":2,"resourceUrl":"/weChatMenu/delete","status":1},{"createBy":1,"createTime":1509517898000,"editable":1,"id":244,"modifyTime":1509517898000,"num":40,"parentId":240,"resourceLevel":4,"resourceName":"菜单同步","resourceType":2,"resourceUrl":"/weChatMenu/syncMenu","status":1},{"createBy":1,"createTime":1509521246000,"editable":1,"id":245,"modifyBy":1,"modifyTime":1509524129000,"num":50,"parentId":240,"resourceLevel":4,"resourceName":"素材获取","resourceType":2,"resourceUrl":"/weChatResource/find","status":1},{"createBy":1,"createTime":1509523735000,"editable":1,"id":256,"modifyBy":1,"modifyTime":1509524149000,"num":60,"parentId":240,"resourceLevel":4,"resourceName":"获取永久素材","resourceType":2,"resourceUrl":"/weChatResource/getMaterial","status":1},{"createBy":1,"createTime":1509099671000,"editable":1,"id":218,"modifyBy":1,"modifyTime":1509100007000,"num":10,"parentId":210,"resourceLevel":4,"resourceName":"用户查询","resourceType":2,"resourceUrl":"/weChatUser/find","status":1},{"createBy":1,"createTime":1509347676000,"editable":1,"id":221,"modifyTime":1509347676000,"num":30,"parentId":210,"resourceLevel":4,"resourceName":"根据openid查询微信用户","resourceType":2,"resourceUrl":"/weChatUser/getUserByOpenid","status":1},{"createBy":1,"createTime":1509347731000,"editable":1,"id":222,"modifyTime":1509347731000,"num":40,"parentId":210,"resourceLevel":4,"resourceName":"同步用户列表","resourceType":2,"resourceUrl":"/weChatUser/syncUser","status":1},{"createBy":1,"createTime":1509347779000,"editable":1,"id":223,"modifyTime":1509347779000,"num":50,"parentId":210,"resourceLevel":4,"resourceName":"修改备注","resourceType":2,"resourceUrl":"/weChatUser/updateRemark","status":1},{"createBy":1,"createTime":1509347841000,"editable":1,"id":224,"modifyTime":1509347841000,"num":60,"parentId":210,"resourceLevel":4,"resourceName":"批量为用户打标签","resourceType":2,"resourceUrl":"/weChatUser/batchtagging","status":1},{"createBy":1,"createTime":1509347893000,"editable":1,"id":225,"modifyTime":1509347893000,"num":70,"parentId":210,"resourceLevel":4,"resourceName":"批量为用户取消标签","resourceType":2,"resourceUrl":"/weChatUser/batchuntagging","status":1},{"createBy":1,"createTime":1510739413000,"editable":1,"id":294,"modifyTime":1510739413000,"num":10,"parentId":212,"resourceLevel":4,"resourceName":"查询","resourceType":2,"resourceUrl":"/tipOffManage/find","status":1},{"createBy":1,"createTime":1510739536000,"editable":1,"id":295,"modifyTime":1510739536000,"num":20,"parentId":212,"resourceLevel":4,"resourceName":"保存","resourceType":2,"resourceUrl":"/tipOffManage/save","status":1},{"createBy":1,"createTime":1510805525000,"editable":1,"id":296,"modifyTime":1510805525000,"num":30,"parentId":212,"resourceLevel":4,"resourceName":"查询单条信息","resourceType":2,"resourceUrl":"/tipOffManage/findById","status":1},{"createBy":1,"createTime":1504229029000,"editable":1,"id":85,"modifyBy":1,"modifyTime":1504230826000,"num":10,"parentId":82,"resourceLevel":4,"resourceName":"查询列表","resourceType":2,"resourceUrl":"/approvalEnterprise/find","status":1},{"createBy":1,"createTime":1504596269000,"editable":1,"id":111,"modifyTime":1504596269000,"num":20,"parentId":82,"resourceLevel":4,"resourceName":"企业审核详情页","resourceType":2,"resourceUrl":"/approvalPage/approvalEnterpriseModifyIndex","status":1},{"createBy":1,"createTime":1504230808000,"editable":1,"id":99,"modifyTime":1504230808000,"num":10,"parentId":97,"resourceLevel":4,"resourceName":"查询列表","resourceType":2,"resourceUrl":"/approvalEnterprise/find","status":1},{"createBy":1,"createTime":1504596322000,"editable":1,"id":112,"modifyTime":1504596322000,"num":20,"parentId":97,"resourceLevel":4,"resourceName":"企业审核记录详情页","resourceType":2,"resourceUrl":"/approvalPage/approvalEnterpriseResultDetails","status":1},{"createBy":1,"createTime":1504229001000,"editable":1,"id":84,"modifyTime":1504229001000,"num":10,"parentId":81,"resourceLevel":4,"resourceName":"查找审核信息","resourceType":2,"resourceUrl":"/approvalProjectInfo/find","status":1},{"createBy":1,"createTime":1504594033000,"editable":1,"id":109,"modifyTime":1504594033000,"num":20,"parentId":81,"resourceLevel":4,"resourceName":"项目信息审核详情","resourceType":2,"resourceUrl":"/approvalPage/approvalProjectInfoDetailIndex","status":1},{"createBy":1,"createTime":1504260068000,"editable":1,"id":103,"modifyTime":1504260068000,"num":10,"parentId":101,"resourceLevel":4,"resourceName":"查询所有审核记录","resourceType":2,"resourceUrl":"/approvalProjectInfo/find","status":1},{"createBy":1,"createTime":1526265810000,"editable":1,"id":341,"modifyBy":1,"modifyTime":1526265872000,"num":10,"parentId":340,"resourceLevel":4,"resourceName":"App版本find","resourceType":2,"resourceUrl":"/appVersion/find","status":1},{"createBy":1,"createTime":1526265850000,"editable":1,"id":342,"modifyBy":1,"modifyTime":1526265958000,"num":20,"parentId":340,"resourceLevel":4,"resourceName":"App版本findById","resourceType":2,"resourceUrl":"/appVersion/findById","status":1},{"createBy":1,"createTime":1526265907000,"editable":1,"id":343,"modifyTime":1526265907000,"num":30,"parentId":340,"resourceLevel":4,"resourceName":"App版本save","resourceType":2,"resourceUrl":"/appVersion/save","status":1},{"createBy":1,"createTime":1526265942000,"editable":1,"id":344,"modifyTime":1526265942000,"num":40,"parentId":340,"resourceLevel":4,"resourceName":"App版本delete","resourceType":2,"resourceUrl":"/appVersion/delete","status":1},{"createBy":1,"createTime":1526276754000,"editable":1,"id":346,"modifyTime":1526276754000,"num":10,"parentId":345,"resourceLevel":4,"resourceName":"网易云IDfind","resourceType":2,"resourceUrl":"/neteaseAccount/find","status":1},{"createBy":1,"createTime":1526276783000,"editable":1,"id":347,"modifyTime":1526276783000,"num":20,"parentId":345,"resourceLevel":4,"resourceName":"网易云IDfindById","resourceType":2,"resourceUrl":"/neteaseAccount/findById","status":1},{"createBy":1,"createTime":1526438975000,"editable":1,"id":350,"modifyTime":1526438975000,"num":40,"parentId":345,"resourceLevel":4,"resourceName":"获取网易云账号","resourceType":2,"resourceUrl":"/neteaseAccount/findByAccount","status":1},{"createBy":1,"createTime":1504661601000,"editable":1,"id":134,"modifyTime":1504661601000,"num":10,"parentId":114,"resourceLevel":3,"resourceName":"查询数量","resourceType":2,"resourceUrl":"/checkInfo/findCheckCount","status":1},{"createBy":1,"createTime":1512112537000,"editable":1,"id":314,"modifyTime":1512112537000,"num":20,"parentId":114,"resourceLevel":3,"resourceName":"查询消息数量","resourceType":2,"resourceUrl":"/sysMessage/unreadCount","status":1},{"createBy":1,"createTime":1504604541000,"editable":1,"id":122,"modifyBy":1,"modifyTime":1504605709000,"num":10,"parentId":117,"resourceLevel":4,"resourceName":"查询计划任务","resourceType":2,"resourceUrl":"/jobCycleRecord/find","status":1},{"createBy":1,"createTime":1504601120000,"editable":1,"id":118,"modifyBy":1,"modifyTime":1504605725000,"num":2,"parentId":115,"resourceLevel":3,"resourceName":"检查是否存在","resourceType":2,"resourceUrl":"/checkInfo/isExist","status":1},{"createBy":1,"createTime":1504601785000,"editable":1,"id":120,"modifyBy":1,"modifyTime":1504605733000,"num":3,"parentId":115,"resourceLevel":3,"resourceName":"保存检查","resourceType":2,"resourceUrl":"/checkInfo/save","status":1},{"createBy":1,"createTime":1504605002000,"editable":1,"id":124,"modifyBy":1,"modifyTime":1504605687000,"num":10,"parentId":121,"resourceLevel":4,"resourceName":"查询检查项","resourceType":2,"resourceUrl":"/checkProject/find","status":1},{"createBy":1,"createTime":1504606412000,"editable":1,"id":130,"modifyTime":1504606412000,"num":20,"parentId":121,"resourceLevel":4,"resourceName":"保存检查项","resourceType":2,"resourceUrl":"/checkProject/save","status":1},{"createBy":1,"createTime":1509325842000,"editable":1,"id":220,"modifyBy":1,"modifyTime":1511166888000,"num":30,"parentId":121,"resourceLevel":4,"resourceName":"检查图片","resourceType":2,"resourceUrl":"/fileUpload/doFileUpload","status":1},{"createBy":1,"createTime":1527581362000,"editable":1,"id":367,"modifyTime":1527581362000,"num":50,"parentId":121,"resourceLevel":4,"resourceName":"生成短信内容","resourceType":2,"resourceUrl":"/checkReport/checkMessage","status":1},{"createBy":1,"createTime":1504605857000,"editable":1,"id":128,"modifyBy":1,"modifyTime":1504606269000,"num":10,"parentId":126,"resourceLevel":4,"resourceName":"企业&项目名称","resourceType":2,"resourceUrl":"/checkProject/findByJcIdAndJcxmId","status":1},{"createBy":1,"createTime":1504606253000,"editable":1,"id":129,"modifyTime":1504606253000,"num":20,"parentId":126,"resourceLevel":4,"resourceName":"查询检查点","resourceType":2,"resourceUrl":"/checkPoint/find","status":1},{"createBy":1,"createTime":1504606526000,"editable":1,"id":131,"modifyTime":1504606526000,"num":30,"parentId":126,"resourceLevel":4,"resourceName":"根据检查项ID查询","resourceType":2,"resourceUrl":"/checkPoint/findByJcxId","status":1},{"createBy":1,"createTime":1504606571000,"editable":1,"id":132,"modifyTime":1504606571000,"num":40,"parentId":126,"resourceLevel":4,"resourceName":"保存检查点","resourceType":2,"resourceUrl":"/checkPoint/save","status":1},{"createBy":1,"createTime":1509094405000,"editable":1,"id":216,"modifyTime":1509094405000,"num":50,"parentId":126,"resourceLevel":4,"resourceName":"上传检查图片","resourceType":2,"resourceUrl":"/fileUpload/doFileUpload","status":1},{"createBy":1,"createTime":1504666125000,"editable":1,"id":136,"modifyBy":1,"modifyTime":1504666198000,"num":10,"parentId":135,"resourceLevel":4,"resourceName":"根据企业ID查询","resourceType":2,"resourceUrl":"/checkInfo/findByQyId","status":1},{"createBy":1,"createTime":1504666175000,"editable":1,"id":137,"modifyTime":1504666175000,"num":20,"parentId":135,"resourceLevel":4,"resourceName":"已检查","resourceType":2,"resourceUrl":"/checkInfo/progressChecked","status":1},{"createBy":1,"createTime":1504666300000,"editable":1,"id":138,"modifyTime":1504666300000,"num":30,"parentId":135,"resourceLevel":4,"resourceName":"未检查","resourceType":2,"resourceUrl":"/checkInfo/progressUncheck","status":1},{"createBy":1,"createTime":1504666427000,"editable":1,"id":140,"modifyTime":1504666427000,"num":10,"parentId":139,"resourceLevel":4,"resourceName":"设备检查记录","resourceType":2,"resourceUrl":"/checkProject/checkRecord","status":1},{"createBy":1,"createTime":1504606614000,"editable":1,"id":133,"modifyTime":1504606614000,"num":10,"parentId":141,"resourceLevel":4,"resourceName":"查看详情","resourceType":2,"resourceUrl":"/checkPoint/findProjectAndPoints","status":1},{"createBy":1,"createTime":1504667926000,"editable":1,"id":143,"modifyTime":1504667926000,"num":10,"parentId":142,"resourceLevel":4,"resourceName":"根据项目ID查询","resourceType":2,"resourceUrl":"/project/findById","status":1},{"createBy":1,"createTime":1511426835000,"editable":1,"id":304,"modifyTime":1511426835000,"num":30,"parentId":142,"resourceLevel":4,"resourceName":"获取安全生产标准树结构","resourceType":2,"resourceUrl":"/baseSafetyHazard/getTree","status":1},{"createBy":1,"createTime":1504747634000,"editable":1,"id":149,"modifyTime":1504747634000,"num":10,"parentId":148,"resourceLevel":4,"resourceName":"根据ID查询企业","resourceType":2,"resourceUrl":"/baseEnterprise/findById","status":1},{"createBy":1,"createTime":1504747680000,"editable":1,"id":150,"modifyTime":1504747680000,"num":20,"parentId":148,"resourceLevel":4,"resourceName":"根据ID查询检查信息","resourceType":2,"resourceUrl":"/checkInfo/findById","status":1},{"createBy":1,"createTime":1504747704000,"editable":1,"id":151,"modifyBy":1,"modifyTime":1511859923000,"num":30,"parentId":148,"resourceLevel":4,"resourceName":"设置整改期限","resourceType":2,"resourceUrl":"/checkInfo/setReCheck","status":1},{"createBy":1,"createTime":1511859275000,"editable":1,"id":308,"modifyTime":1511859275000,"num":20,"parentId":152,"resourceLevel":4,"resourceName":"项目查询","resourceType":2,"resourceUrl":"/checkProject/findReCheckProject","status":1},{"createBy":1,"createTime":1504860967000,"editable":1,"id":157,"modifyTime":1504860967000,"num":10,"parentId":156,"resourceLevel":4,"resourceName":"移动端保存项目","resourceType":2,"resourceUrl":"/checkProject/saveCK","status":1},{"createBy":1,"createTime":1511860000000,"editable":1,"id":309,"modifyTime":1511860000000,"num":10,"parentId":302,"resourceLevel":4,"resourceName":"查询复查点","resourceType":2,"resourceUrl":"/checkPoint/findReCheckPoint","status":1},{"createBy":1,"createTime":1511170876000,"editable":1,"id":303,"modifyBy":1,"modifyTime":1511171098000,"num":16,"parentId":115,"resourceLevel":3,"resourceName":"保存图片","resourceType":2,"resourceUrl":"https://120.25.251.167:1443/fileUpload/doFileUpload","status":1},{"createBy":1,"createTime":1506810519000,"editable":1,"id":173,"modifyTime":1506810519000,"num":10,"parentId":166,"resourceLevel":4,"resourceName":"查询","resourceType":2,"resourceUrl":"/baseEnterprise/find","status":1},{"createBy":1,"createTime":1506811425000,"editable":1,"id":178,"modifyTime":1506811425000,"num":10,"parentId":177,"resourceLevel":5,"resourceName":"根据id查询企业详情","resourceType":2,"resourceUrl":"/baseEnterprise/findById","status":1},{"createBy":1,"createTime":1510019658000,"editable":1,"id":271,"modifyTime":1510019658000,"num":5,"parentId":179,"resourceLevel":5,"resourceName":"获取检查记录","resourceType":2,"resourceUrl":"/baseEnterprise/queryCheckRecords","status":1},{"createBy":1,"createTime":1506812912000,"editable":1,"id":193,"modifyTime":1506812912000,"num":10,"parentId":190,"resourceLevel":5,"resourceName":"企业信息修改","resourceType":2,"resourceUrl":"/baseEnterprise/save","status":1},{"createBy":1,"createTime":1506812965000,"editable":1,"id":194,"modifyTime":1506812965000,"num":50,"parentId":166,"resourceLevel":4,"resourceName":"删除","resourceType":2,"resourceUrl":"/baseEnterprise/delete","status":1},{"createBy":1,"createTime":1507511401000,"editable":1,"id":196,"modifyTime":1507511401000,"num":10,"parentId":168,"resourceLevel":4,"resourceName":"查询","resourceType":2,"resourceUrl":"/community/find","status":1},{"createBy":1,"createTime":1507512028000,"editable":1,"id":199,"modifyTime":1507512028000,"num":10,"parentId":197,"resourceLevel":5,"resourceName":"查询企业详细信息","resourceType":2,"resourceUrl":"/community/findById","status":1},{"createBy":1,"createTime":1507512122000,"editable":1,"id":200,"modifyTime":1507512122000,"num":20,"parentId":197,"resourceLevel":5,"resourceName":"查询企业所属人员","resourceType":2,"resourceUrl":"/baseStaff/findStaff","status":1},{"createBy":1,"createTime":1507512534000,"editable":1,"id":203,"modifyTime":1507512534000,"num":10,"parentId":202,"resourceLevel":5,"resourceName":"搜索","resourceType":2,"resourceUrl":"/baseEnterprise/find","status":1},{"createBy":1,"createTime":1507512607000,"editable":1,"id":204,"modifyTime":1507512607000,"num":50,"parentId":168,"resourceLevel":4,"resourceName":"删除","resourceType":2,"resourceUrl":"/community/delete","status":1},{"createBy":1,"createTime":1506811616000,"editable":1,"id":180,"modifyBy":1,"modifyTime":1506811647000,"num":10,"parentId":167,"resourceLevel":4,"resourceName":"人员列表查询","resourceType":2,"resourceUrl":"/baseStaff/find","status":1},{"createBy":1,"createTime":1506810902000,"editable":1,"id":175,"modifyTime":1506810902000,"num":10,"parentId":174,"resourceLevel":6,"resourceName":"企业详细信息查询","resourceType":2,"resourceUrl":"/baseEnterprise/findById","status":1},{"createBy":1,"createTime":1506812850000,"editable":1,"id":192,"modifyTime":1506812850000,"num":10,"parentId":191,"resourceLevel":6,"resourceName":"保存","resourceType":2,"resourceUrl":"/baseEnterprise/save","status":1},{"createBy":1,"createTime":1506813016000,"editable":1,"id":195,"modifyTime":1506813016000,"num":40,"parentId":171,"resourceLevel":5,"resourceName":"删除","resourceType":2,"resourceUrl":"/baseEnterprise/delete","status":1},{"createBy":1,"createTime":1506810455000,"editable":1,"id":172,"modifyTime":1506810455000,"num":20,"parentId":169,"resourceLevel":4,"resourceName":"多维度查询接口","resourceType":2,"resourceUrl":"/baseEnterprise/find","status":1},{"createBy":1,"createTime":1512094623000,"editable":1,"id":311,"modifyTime":1512094623000,"num":10,"parentId":310,"resourceLevel":4,"resourceName":"消息查询","resourceType":2,"resourceUrl":"/sysMessage/find","status":1},{"createBy":1,"createTime":1512094654000,"editable":1,"id":312,"modifyBy":1,"modifyTime":1512094814000,"num":20,"parentId":310,"resourceLevel":4,"resourceName":"单条消息查询","resourceType":2,"resourceUrl":"/sysMessage/findById","status":1},{"createBy":1,"createTime":1512094785000,"editable":1,"id":313,"modifyTime":1512094785000,"num":30,"parentId":310,"resourceLevel":4,"resourceName":"标记已读","resourceType":2,"resourceUrl":"/sysMessage/markRead","status":1},{"createBy":1,"createTime":1506811745000,"editable":1,"id":181,"modifyTime":1506811745000,"num":7,"parentId":2,"resourceLevel":2,"resourceName":"信息页","resourceType":2,"resourceUrl":"","status":1},{"createBy":1,"createTime":1506812065000,"editable":1,"id":186,"modifyTime":1506812065000,"num":10,"parentId":182,"resourceLevel":4,"resourceName":"企业信息详情","resourceType":2,"resourceUrl":"/baseEnterprise/findById","status":1},{"createBy":1,"createTime":1506811930000,"editable":1,"id":184,"modifyTime":1506811930000,"num":10,"parentId":183,"resourceLevel":4,"resourceName":"人员详情","resourceType":2,"resourceUrl":"/baseStaff/findById","status":1},{"createBy":1,"createTime":1506811996000,"editable":1,"id":185,"modifyTime":1506811996000,"num":20,"parentId":183,"resourceLevel":4,"resourceName":"获取所属单位","resourceType":2,"resourceUrl":"/baseStaff/findSsdw","status":1},{"createBy":1,"createTime":1506812104000,"editable":1,"id":187,"modifyTime":1506812104000,"num":8,"parentId":2,"resourceLevel":2,"resourceName":"添加信息","resourceType":2,"resourceUrl":"","status":1},{"createBy":1,"createTime":1506812450000,"editable":1,"id":189,"modifyTime":1506812450000,"num":10,"parentId":188,"resourceLevel":4,"resourceName":"信息添加","resourceType":2,"resourceUrl":"/baseEnterprise/save","status":1}]
     * menuList : [{"children":[],"createBy":1,"createTime":1504598255000,"editable":1,"id":114,"modifyTime":1504598255000,"num":1,"parentId":2,"resourceLevel":2,"resourceName":"移动端首页","resourceType":1,"resourceUrl":"/mobile/index","status":1},{"children":[{"children":[{"children":[],"createBy":1,"createTime":1509950695000,"editable":1,"id":264,"modifyTime":1509950695000,"num":20,"parentId":117,"resourceLevel":4,"resourceName":"扫描二维码","resourceType":1,"resourceUrl":"/mobile/check/barcodeScan","status":1}],"createBy":1,"createTime":1504599337000,"editable":1,"id":117,"modifyBy":1,"modifyTime":1504605572000,"num":1,"parentId":115,"resourceLevel":3,"resourceName":"新建流程","resourceType":1,"resourceUrl":"/mobile/check/newCheck","status":1},{"children":[{"children":[],"createBy":1,"createTime":1509951111000,"editable":1,"id":265,"modifyTime":1509951111000,"num":40,"parentId":121,"resourceLevel":4,"resourceName":"扫描二维码","resourceType":1,"resourceUrl":"/mobile/check/barcodeScan","status":1}],"createBy":1,"createTime":1504602356000,"editable":1,"id":121,"modifyBy":1,"modifyTime":1504605673000,"num":4,"parentId":115,"resourceLevel":3,"resourceName":"检查项页面","resourceType":1,"resourceUrl":"/mobile/check/newCheckContent","status":1},{"children":[],"createBy":1,"createTime":1504605821000,"editable":1,"id":126,"modifyBy":1,"modifyTime":1504606083000,"num":5,"parentId":115,"resourceLevel":3,"resourceName":"检查点页面","resourceType":1,"resourceUrl":"/mobile/check/equrimentCheck","status":1},{"children":[],"createBy":1,"createTime":1504666087000,"editable":1,"id":135,"modifyTime":1504666087000,"num":6,"parentId":115,"resourceLevel":3,"resourceName":"检查进度","resourceType":1,"resourceUrl":"/mobile/check/checkProgress","status":1},{"children":[],"createBy":1,"createTime":1504666383000,"editable":1,"id":139,"modifyTime":1504666383000,"num":7,"parentId":115,"resourceLevel":3,"resourceName":"项目检查记录","resourceType":1,"resourceUrl":"/mobile/check/checkRecord","status":1},{"children":[],"createBy":1,"createTime":1504666551000,"editable":1,"id":141,"modifyTime":1504666551000,"num":8,"parentId":115,"resourceLevel":3,"resourceName":"项目检查结果","resourceType":1,"resourceUrl":"/mobile/check/equCheckCons","status":1},{"children":[{"children":[],"createBy":1,"createTime":1510641012000,"editable":1,"id":292,"modifyTime":1510641012000,"num":20,"parentId":142,"resourceLevel":4,"resourceName":"获取项目检查结果","resourceType":1,"resourceUrl":"/project/getByMapFind","status":1}],"createBy":1,"createTime":1504667681000,"editable":1,"id":142,"modifyTime":1504667681000,"num":9,"parentId":115,"resourceLevel":3,"resourceName":"项目详情","resourceType":1,"resourceUrl":"/mobile/check/projectDetail","status":1},{"children":[],"createBy":1,"createTime":1504747562000,"editable":1,"id":148,"modifyTime":1504747562000,"num":10,"parentId":115,"resourceLevel":3,"resourceName":"检查记录","resourceType":1,"resourceUrl":"/mobile/check/checkRecordContent","status":1},{"children":[],"createBy":1,"createTime":1504747821000,"editable":1,"id":152,"modifyTime":1504747821000,"num":11,"parentId":115,"resourceLevel":3,"resourceName":"新建复查","resourceType":1,"resourceUrl":"/mobile/check/reCheck","status":1},{"children":[],"createBy":1,"createTime":1504747848000,"editable":1,"id":153,"modifyTime":1504747848000,"num":12,"parentId":115,"resourceLevel":3,"resourceName":"复查目录","resourceType":1,"resourceUrl":"/mobile/check/reCheckContent","status":1},{"children":[],"createBy":1,"createTime":1504860932000,"editable":1,"id":156,"modifyTime":1504860932000,"num":13,"parentId":115,"resourceLevel":3,"resourceName":"添加项目","resourceType":1,"resourceUrl":"/mobile/basePage/addProject","status":1},{"children":[],"createBy":1,"createTime":1509950397000,"editable":1,"id":263,"modifyBy":1,"modifyTime":1509950550000,"num":14,"parentId":115,"resourceLevel":3,"resourceName":"二维码扫描","resourceType":1,"resourceUrl":"/mobile/check/barcodeScan","status":1},{"children":[],"createBy":1,"createTime":1511162576000,"editable":1,"id":302,"modifyBy":1,"modifyTime":1511162596000,"num":15,"parentId":115,"resourceLevel":3,"resourceName":"复查页面","resourceType":1,"resourceUrl":"/mobile/check/reCheckProject","status":1}],"createBy":1,"createTime":1504598838000,"editable":1,"id":115,"modifyBy":1,"modifyTime":1504599743000,"num":2,"parentId":2,"resourceLevel":2,"resourceName":"检查","resourceType":1,"resourceUrl":"","status":1},{"children":[{"children":[{"children":[],"createBy":1,"createTime":1506811321000,"editable":1,"id":177,"modifyTime":1506811321000,"num":20,"parentId":166,"resourceLevel":4,"resourceName":"企业基础信息页","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseBasicInfo","status":1},{"children":[],"createBy":1,"createTime":1506811505000,"editable":1,"id":179,"modifyTime":1506811505000,"num":30,"parentId":166,"resourceLevel":4,"resourceName":"企业检查记录页","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseCheckRecord","status":1},{"children":[],"createBy":1,"createTime":1506812748000,"editable":1,"id":190,"modifyTime":1506812748000,"num":40,"parentId":166,"resourceLevel":4,"resourceName":"企业信息修改页","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseInfoModify","status":1}],"createBy":1,"createTime":1506809292000,"editable":1,"id":166,"modifyBy":1,"modifyTime":1509005500000,"num":10,"parentId":161,"resourceLevel":3,"resourceName":"企业信息查询","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseInfoIndex","status":1},{"children":[{"children":[],"createBy":1,"createTime":1507511689000,"editable":1,"id":197,"modifyBy":1,"modifyTime":1507512410000,"num":20,"parentId":168,"resourceLevel":4,"resourceName":"社区基础信息页","resourceType":1,"resourceUrl":"/mobile/basePage/communityBasicInfo","status":1},{"children":[],"createBy":1,"createTime":1507512350000,"editable":1,"id":201,"modifyTime":1507512350000,"num":30,"parentId":168,"resourceLevel":4,"resourceName":"社区信息修改页","resourceType":1,"resourceUrl":"/mobile/basePage/modifyCommunityInfo","status":1},{"children":[],"createBy":1,"createTime":1507512472000,"editable":1,"id":202,"modifyTime":1507512472000,"num":40,"parentId":168,"resourceLevel":4,"resourceName":"社区企业查询","resourceType":1,"resourceUrl":"/mobile/basePage/communityEnterpriseInfo","status":1}],"createBy":1,"createTime":1506809818000,"editable":1,"id":168,"modifyBy":1,"modifyTime":1507512251000,"num":20,"parentId":161,"resourceLevel":3,"resourceName":"社区信息检查","resourceType":1,"resourceUrl":"/mobile/basePage/communityInfoIndex","status":1},{"children":[],"createBy":1,"createTime":1506809545000,"editable":1,"id":167,"modifyTime":1506809545000,"num":30,"parentId":161,"resourceLevel":3,"resourceName":"人员信息查询","resourceType":1,"resourceUrl":"/mobile/basePage/staffInfoIndex","status":1},{"children":[{"children":[{"children":[],"createBy":1,"createTime":1506810751000,"editable":1,"id":174,"modifyTime":1506810751000,"num":10,"parentId":171,"resourceLevel":5,"resourceName":"企业基本信息页","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseBasicInfo","status":1},{"children":[],"createBy":1,"createTime":1506811011000,"editable":1,"id":176,"modifyTime":1506811011000,"num":20,"parentId":171,"resourceLevel":5,"resourceName":"企业检查记录页","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseCheckRecord","status":1},{"children":[],"createBy":1,"createTime":1506812793000,"editable":1,"id":191,"modifyTime":1506812793000,"num":30,"parentId":171,"resourceLevel":5,"resourceName":"企业细信息修改页","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseInfoModify","status":1}],"createBy":1,"createTime":1506810160000,"editable":1,"id":171,"modifyTime":1506810160000,"num":10,"parentId":169,"resourceLevel":4,"resourceName":"查询结果页","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseMultiQuery","status":1}],"createBy":1,"createTime":1506809949000,"editable":1,"id":169,"modifyTime":1506809949000,"num":40,"parentId":161,"resourceLevel":3,"resourceName":"企业多维度查询","resourceType":1,"resourceUrl":"/mobile/basePage/enterpriseMultiQuery","status":1},{"children":[],"createBy":1,"createTime":1506810026000,"editable":1,"id":170,"modifyBy":1,"modifyTime":1506810104000,"num":50,"parentId":161,"resourceLevel":3,"resourceName":"检查项目多维度查询","resourceType":1,"resourceUrl":"/mobile/basePage/projectMultiQuery","status":1}],"createBy":1,"createTime":1506760737000,"editable":1,"id":161,"modifyBy":1,"modifyTime":1506760754000,"num":3,"parentId":2,"resourceLevel":2,"resourceName":"查询","resourceType":1,"resourceUrl":"/mobile/index/query","status":1},{"children":[{"children":[],"createBy":1,"createTime":1506763856000,"editable":1,"id":163,"modifyTime":1506763856000,"num":1,"parentId":162,"resourceLevel":3,"resourceName":"检查计划","resourceType":1,"resourceUrl":"/mobile/basePage/checkPlan","status":1},{"children":[],"createBy":1,"createTime":1512094589000,"editable":1,"id":310,"modifyTime":1512094589000,"num":2,"parentId":162,"resourceLevel":3,"resourceName":"消息管理","resourceType":1,"resourceUrl":"/mobile/basePage/message","status":1},{"children":[],"createBy":1,"createTime":1509412960000,"editable":1,"id":232,"modifyTime":1509412960000,"num":3,"parentId":162,"resourceLevel":3,"resourceName":"系统设置","resourceType":1,"resourceUrl":"/mobile/basePage/systermSetting","status":1}],"createBy":1,"createTime":1506760793000,"editable":1,"id":162,"modifyTime":1506760793000,"num":4,"parentId":2,"resourceLevel":2,"resourceName":"我的","resourceType":1,"resourceUrl":"/mobile/index/my","status":1},{"children":[],"createBy":1,"createTime":1506764223000,"editable":1,"id":164,"modifyTime":1506764223000,"num":5,"parentId":2,"resourceLevel":2,"resourceName":"添加企业","resourceType":1,"resourceUrl":"/mobile/basePage/addEnterprise","status":1},{"children":[],"createBy":1,"createTime":1506765774000,"editable":1,"id":165,"modifyTime":1506765774000,"num":6,"parentId":2,"resourceLevel":2,"resourceName":"视频通话","resourceType":1,"resourceUrl":"/mobile/basePage/call","status":1}]
     * userName : 夏庄街道安监小组
     */

    private AccountBean account;
    private String userName;
    private List<ComponentListBean> componentList;
    private List<MenuListBean> menuList;

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ComponentListBean> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<ComponentListBean> componentList) {
        this.componentList = componentList;
    }

    public List<MenuListBean> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuListBean> menuList) {
        this.menuList = menuList;
    }

    public static class AccountBean {
        /**
         * createBy : 1
         * createTime : 1501480099000
         * id : 1
         * loginName : admin
         * loginType : 2
         * modifyBy : 1
         * modifyTime : 1520910653000
         * roleId : 1
         * ssId : 1
         * status : 1
         */

        private int createBy;
        private long createTime;
        private int id;
        private String loginName;
        private int loginType;
        private int modifyBy;
        private long modifyTime;
        private int roleId;
        private int ssId;
        private int status;

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public int getLoginType() {
            return loginType;
        }

        public void setLoginType(int loginType) {
            this.loginType = loginType;
        }

        public int getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(int modifyBy) {
            this.modifyBy = modifyBy;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public int getSsId() {
            return ssId;
        }

        public void setSsId(int ssId) {
            this.ssId = ssId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class ComponentListBean {
        /**
         * createBy : 1
         * createTime : 1510133835000
         * editable : 1
         * id : 273
         * modifyTime : 1510133835000
         * num : 10
         * parentId : 3
         * resourceLevel : 3
         * resourceName : 系统消息查询
         * resourceType : 2
         * resourceUrl : /sysMessage/find
         * status : 1
         * modifyBy : 1
         */

        private int createBy;
        private long createTime;
        private int editable;
        private int id;
        private long modifyTime;
        private int num;
        private int parentId;
        private int resourceLevel;
        private String resourceName;
        private int resourceType;
        private String resourceUrl;
        private int status;
        private int modifyBy;

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getEditable() {
            return editable;
        }

        public void setEditable(int editable) {
            this.editable = editable;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getResourceLevel() {
            return resourceLevel;
        }

        public void setResourceLevel(int resourceLevel) {
            this.resourceLevel = resourceLevel;
        }

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }

        public int getResourceType() {
            return resourceType;
        }

        public void setResourceType(int resourceType) {
            this.resourceType = resourceType;
        }

        public String getResourceUrl() {
            return resourceUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(int modifyBy) {
            this.modifyBy = modifyBy;
        }
    }

    public static class MenuListBean {
        /**
         * children : []
         * createBy : 1
         * createTime : 1504598255000
         * editable : 1
         * id : 114
         * modifyTime : 1504598255000
         * num : 1
         * parentId : 2
         * resourceLevel : 2
         * resourceName : 移动端首页
         * resourceType : 1
         * resourceUrl : /mobile/index
         * status : 1
         * modifyBy : 1
         */

        private int createBy;
        private long createTime;
        private int editable;
        private int id;
        private long modifyTime;
        private int num;
        private int parentId;
        private int resourceLevel;
        private String resourceName;
        private int resourceType;
        private String resourceUrl;
        private int status;
        private int modifyBy;
        private List<?> children;

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getEditable() {
            return editable;
        }

        public void setEditable(int editable) {
            this.editable = editable;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getResourceLevel() {
            return resourceLevel;
        }

        public void setResourceLevel(int resourceLevel) {
            this.resourceLevel = resourceLevel;
        }

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }

        public int getResourceType() {
            return resourceType;
        }

        public void setResourceType(int resourceType) {
            this.resourceType = resourceType;
        }

        public String getResourceUrl() {
            return resourceUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getModifyBy() {
            return modifyBy;
        }

        public void setModifyBy(int modifyBy) {
            this.modifyBy = modifyBy;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
