package com.hg.gama.boot.config;

import com.hg.gama.gamautil.numasutil.constants.BaseCode;
import org.springframework.context.annotation.Configuration;

/**
 * 异常错误码类 大写命名，用下划线'_'做分割 映射的property文件的key用点'.'做分割
 */
@Configuration
public class CodeConstants implements BaseCode {
    // 服务端业务异常, code区间
    // 120001 - 121000 //蔡振森
    // 121001 - 122000 //nursecare_duty 	
    // 122001 - 123000
    // 123001 - 124000
    // 124001 - 125000
    // 125001 - 126000 //舒健苗
    // 126001 - 127000 // 付强 train模块
    // 127001 - 128000 //祝靖俊
    // 128001 - 129000 //龙璇
    // 129001 - 129999 //郑尚达


    /* SECURITY 开始 1-999 */
    // 用户不存在
    public static final int PLT_SECURITY_LOGIN_UNKNOW = SERVER_ERROR + SECURITY + 1;
    // 账号被锁定
    public static final int PLT_SECURITY_LOGIN_LOCKED = SERVER_ERROR + SECURITY + 2;
    // 账号被禁用
    public static final int PLT_SECURITY_LOGIN_DISABLED = SERVER_ERROR + SECURITY + 3;
    // 密码错误
    public static final int PLT_SECURITY_LOGIN_PASSWORD_ERROR = SERVER_ERROR + SECURITY + 4;
    // 用户名或密码错误
    public static final int PLT_SECURITY_LOGIN_ACCOUNT_ERROR = SERVER_ERROR + SECURITY + 5;//auth
    // 用户不存在
    public static final int USER_UNEXIST_ERROR = SERVER_ERROR + SECURITY + 6;//jpa
    // 护理单元不存在
    public static final int NURSEUNIT_UNEXIST_ERROR = SERVER_ERROR + SECURITY + 7;//jpa
    // 登入超时错误
    public static final int PLT_SESSION_TIMEOUT = SERVER_ERROR + SECURITY + 8;
    // Platform异常
    public static final int PLT_USER_WITHOUT_UNIT = SERVER_ERROR + SECURITY + 9;//jpa
    // 用户{0}已存在
    public static final int USER_EXISTED_ERROR = SERVER_ERROR + SECURITY + 10;//jpa

    //    // 参数校验错误，参数不能为空
    //    public static final int PARAMETERS_CHECK_NULL_ERROR = SERVER_ERROR + SECURITY + 11;

    //    // 参数校验错误，ID无效
    //    public static final int PARAMETERS_CHECK_ID_ERROR = SERVER_ERROR + SECURITY + 12;

    //    // 参数均为null
    //    public static final int PARAMETERS_ALL_NULL_ERROR = SERVER_ERROR + SECURITY + 13;

    //登录失效
    public static final int UNAUTH_LOGIN = SERVER_ERROR + SECURITY + 14;//util/boot

    //登录token无效
    public static final int UNAUTH_TOKEN_INVALID = SERVER_ERROR + SECURITY + 15;//boot

    //    // 注解校验错误
    //    public static final int PARAMETERS_VALIDATE_COMMON_ERROR = SERVER_ERROR + SECURITY + 16;

    // 文件上传失败
    public static final int UPLOAD_ERROR = SERVER_ERROR + SECURITY + 17;//jpa/employee

    // 护理单元无法删除
    public static final int PLT_ORGANIZATION_CAN_NOT_DELETE = SERVER_ERROR + SECURITY + 18;//jpa

    // 护理单元不存在
    public static final int PLT_ORGANIZATION_UNKNOW_ID = SERVER_ERROR + SECURITY + 19;//jpa

    // 护理单元不存在
    public static final int PLT_ORGANIZATION_UNKNOW_CODE = SERVER_ERROR + SECURITY + 20;//jpa

    // 护理单元重复
    public static final int PLT_ORGANIZATION_DUPLICATE = SERVER_ERROR + SECURITY + 21;//jpa

    // 权限重复
    public static final int PLT_PERMISSION_DUPLICATE = SERVER_ERROR + SECURITY + 22;//jpa

    // 密码错误
    public static final int PLT_USER_PASSWORD_ERROR = SERVER_ERROR + SECURITY + 23;//jpa

    // 密码为空
    public static final int PLT_USER_EMPTY_PASSWORD = SERVER_ERROR + SECURITY + 24;//jpa

    // 无法找到用户
    public static final int PLT_USER_UNKNOW_ID = SERVER_ERROR + SECURITY + 25;//jpa

    // 用户重复
    public static final int PLT_USER_DUPLICATE = SERVER_ERROR + SECURITY + 26;//jpa

    // 角色不存在
    public static final int PLT_ROLE_UNKNOW_CODE = SERVER_ERROR + SECURITY + 27;//jpa

    // 角色重复
    public static final int PLT_ROLE_DUPLICATE = SERVER_ERROR + SECURITY + 28;//jpa

    //    // 找不到频次
    //    public static final int PLT_FREQUENCY_UNKNOW_ID = SERVER_ERROR + SECURITY + 25;//duty

    //    // 对象不存在
    //    public static final int PLT_OBJECT_NOT_EXISTED = SERVER_ERROR + SECURITY + 29;

    //    // 假期类型不存在
    //    public static final int PLT_TYPE_UNKNOW_ID = SERVER_ERROR + SECURITY + 25;//duty

    //    //
    //    public static final int QOS_SATIS_SURVEYINDEX_NO_FILLED_USER = SERVER_ERROR + SECURITY + 25;//qos

    public static final int PLT_NAVMENU_DUPLICATE = SERVER_ERROR + SECURITY + 30;//jpa

    public static final int PLT_NAVMENU_UNKNOW_ID = SERVER_ERROR + SECURITY + 31;//jpa




    /* SECURITY 结束 */

    /* BIZ 开始 */
    // 部门绩效标准指标不存在
    //    public static final int KPI_DEPT_STANDARD_INDEX_UNEXIST = SERVER_ERROR + BIZ + 1;

    //    public static final int QUERY_TYPE_UNEXIST = SERVER_ERROR + BIZ + 2;
    //对象不存在
    public static final int OBJECT_UNEXIST = SERVER_ERROR + BIZ + 3;//jpa

    //当前用户的用户权限未清空，无法复制
    public static final int USER_PERMISSION_EXIST_COPY_FAILED = SERVER_ERROR + BIZ + 4;//jpa

    //    public static final int KPI_DEPT_UNIT_STANDARD_UNEXIST = SERVER_ERROR + BIZ + 5;

    //    public static final int KPI_DEPT_UNIT_STANDARD_INDEX_UNEXIST = SERVER_ERROR + BIZ + 6;

    //    public static final int KPI_PER_NURSE_FORM_UNEXIST = SERVER_ERROR + BIZ + 7;

    //    public static final int KPI_PER_NURSE_FORM_INDEX_UNEXIST = SERVER_ERROR + BIZ + 8;

    //    public static final int KPI_PER_NURSE_FORM_NURSE_CONFIRM_ERROR = SERVER_ERROR + BIZ + 9;

    //    public static final int KPI_PER_NURSE_FORM_UNSENDED = SERVER_ERROR + BIZ + 10;

    //    public static final int KPI_PER_NURSE_FORM_CHECK_ERROR = SERVER_ERROR + BIZ + 11;

    //    public static final int KPI_PER_NURSE_FORM_EVALUATE_SELF_ERROR = SERVER_ERROR + BIZ + 12;

    //    public static final int KPI_PER_NURSE_FORM_SENIOR_MATCH_ERROR = SERVER_ERROR + BIZ + 13;

    //    public static final int KPI_PER_COEFF_UNEXIST = SERVER_ERROR + BIZ + 14;

    //    public static final int KPI_PER_COEFF_EXISTED = SERVER_ERROR + BIZ + 15;

    //    public static final int KPI_DEPT_SIM_COEFF_UNIT_EXIST = SERVER_ERROR + BIZ + 16;

    //    public static final int KPI_DEPT_COMM_COEFF_EXIST = SERVER_ERROR + BIZ + 17;

    //    public static final int KPI_DEPT_SIM_STANDARD_UNSETTING = SERVER_ERROR + BIZ + 18;


    /* BIZ 结束 */

    /* DB 开始 */

    /* DB 结束 */

    /*
     * 付强 exam模块
     *****************************************************************************************/
    /* BIZ 开始 */
    //    // 不是当前允许考试的客户端
    //    public final static int EXAM_ONLINE_NOT_ALLOW_IP = SERVER_ERROR + BIZ + 4001;
    //
    //    public final static int EXAM_CHAPTER_NAME_HAS_EXIST = SERVER_ERROR + BIZ + 4002;
    //
    //    public final static int EXAM_ALLOW_IP_NOT_SAME_DOMAIN = SERVER_ERROR + BIZ + 4003;
    //
    //    public final static int EXAM_USER_PAPER_NOT_END_NOT_ALLOW_VIEW = SERVER_ERROR + BIZ + 4004;
    //
    //    public final static int EXAM_USER_PAPER_HAS_SUBMIT = SERVER_ERROR + BIZ + 4005;
    //
    //    public final static int EXAM_USER_PAPER_NOT_IN_EXAMINATION_PERIOD = SERVER_ERROR + BIZ + 4006;
    //
    //    public final static int EXAM_PAPER_HAS_ANSWER = SERVER_ERROR + BIZ + 4007;
    //
    //    public final static int EXAM_USER_PAPER_NOT_ALLOW_VIEW = SERVER_ERROR + BIZ + 4008;
    //
    //    public final static int EXAM_EXIST = SERVER_ERROR + BIZ + 4009;
    //
    //    public final static int EXAM_NO_EXAMINER = SERVER_ERROR + BIZ + 4010;
    /* BIZ 结束 */

    /* 舒健苗 *****************************************************************************************/
    /* BIZ 开始 */
    // 用户头像不存在
    public static final int EMP_PORTRAIT_NOT_EXIST = SERVER_ERROR + BIZ + 5001;//jpa
    //    // employee已存在
    //    public final static int EMP_ARCHIVE_ALREADY_EXISTS = SERVER_ERROR + BIZ + 5002;
    //    // 未维护默认工号数据表
    //    public final static int EMP_DEFAULT_CODE_TABLE_NOT_EXIST = SERVER_ERROR + BIZ + 5003;
    // 通过二维码未查询到指定员工
    public static final int EMP_ARCHIVE_QRSCAN_NOT_FOUND = SERVER_ERROR + BIZ + 5004;//jpa
    // 格式错误，请直接传输扫描后的内容
    public static final int EMP_ARCHIVE_QRSCAN_FORMAT_MISMATCH = SERVER_ERROR + BIZ + 5005;//jpa

    //    //调动记录补录结束时间需小于今天
    //    public final static int EMP_TRANSFER_ENDDATE_NOT_BEFORE_TODAY = SERVER_ERROR + BIZ + 5006;
    //    //调动记录补录结束时间需小于最新的调动记录时间
    //    public final static int EMP_TRANSFER_ENDDATE_NOT_BEFORE_LAST_TRANSFER = SERVER_ERROR + BIZ + 5007;
    //    //调动记录结束时间需大于等于入院日期
    //    public final static int EMP_TRANSFER_ENDDATE_BEFORE_CHECKINDATE = SERVER_ERROR + BIZ + 5008;
    //    //调动记录补录结束时间需大于等于最新的调动记录时间
    //    public final static int EMP_TRANSFER_ENDDATE_BEFORE_LAST_TRANSFER = SERVER_ERROR + BIZ + 5009;
    //    //开始时间需小于等于结束时间
    //    public final static int EMP_TRANSFER_BEGINDATE_NOT_AFTER_ENDDATE = SERVER_ERROR + BIZ + 5010;
    //    //已有预调动记录，请先撤销！
    //    public final static int EMP_HAD_PRE_TRANSFER = SERVER_ERROR + BIZ + 5013;

    //    //工号已存在,无法撤销注销
    //    public final static int EMP_CODE_ALREADY_EXISTS_CANNOT_DISMISS = SERVER_ERROR + BIZ + 5011;

    //    // 默认督导规则不存在，请先维护督导规则
    //    public final static int EDU_DEFAULT_RULE_NOT_EXIST = SERVER_ERROR + BIZ + 5101;

    //    //不能在护理单元内部进行调岗
    //    public final static int EMP_TRANSFER_SAME_UNIT_FORBIDDEN = SERVER_ERROR + BIZ + 5454;

    //    //bug#24588 修改弹出信息
    //    public final static int SINGLE_EXAM_ALREADY_ESTABLISHED = SERVER_ERROR + BIZ + 5459;

    /* BIZ 结束 */

    /* DB 开始 */

    /* DB 结束 */

    /*
     * 付强 train模块
     *****************************************************************************************/
    /* BIZ 开始 */
    // 护理单元已经配置了模板
    //    public final static int TRAIN_NURSEUNIT_TPL_HAS_EXIST = SERVER_ERROR + BIZ + 6001;
    //    public final static int TRAIN_PLAN_TIME_HAS_EXIST = SERVER_ERROR + BIZ + 6002;

    /* BIZ 结束 */

    /* DB 开始 */

    /* DB 结束 */

    /* 祝靖俊 *****************************************************************************************/
    /* BIZ 开始 */
    //    public static final int DUTY_PLAN_CANNOT_BE_DELETED = SERVER_ERROR + BIZ + 7001;
    //
    //    public static final int DUTY_PLAN_NOT_FOUND = SERVER_ERROR + BIZ + 7002;

    //    public static final int OA_LEAVE_INTERN_TYPE_ERROR = SERVER_ERROR + BIZ + 7003;

    public static final int FILE_NOT_EXISTED = SERVER_ERROR + BIZ + 7004;//jpa

    //    public static final int DUTY_PLAN_CANNOT_BE_SUBMITTED_BEFORE_END = 7005;

    //    // 已存在最小值班数的异常
    //    public static final int DUTY_MIN_HOUR_CFG_DUPLICATE = 7006;
    //
    //    // 已存在最小值班数的异常
    //    public static final int DUTY_NORMAL_HOUR_CFG_DUPLICATE = 7007;
    //
    //    // 已存在假期配置的异常
    //    public static final int DUTY_WEEK_HOLIDAY_CFG_DUPLICATE = 7007;
    //
    //    // 已存在周排班设置的异常
    //    public static final int SHIFT_WEEK_REQUIRE_DUPLICATE = 7008;

    /* BIZ 结束 */

    /* DB 开始 */
    /* DB 结束 */

    /* 龙璇 *****************************************************************************************/
    /* BIZ 开始 */
    //    // 下载文件不存在导致下载出现错误
    //    public static final int SYS_FILE_DOWNLOAD_ERROR = SERVER_ERROR + BIZ + 8001;
    //    // 统计过程中总人员为空导致错误
    //    public static final int DOC_FIND_AND_COUNT_MEMBERS_NULL_ERROR = SERVER_ERROR + BIZ + 8002;
    // 护理单元不存在护士长引发的错误
    public static final int NURSE_UNIT_WITH_NO_MATRON_ERROR = SERVER_ERROR + BIZ + 8003;//jpa
    //    // 当前人员没有个人档案
    //    public static final int NURSE_WITH_NO_EMPLOYEE_ID = SERVER_ERROR + BIZ + 8004;
    // 请选中一个数据类型名称
    public static final int BUSINESS_UNIT_MANAGEMENT_TYPE = SERVER_ERROR + BIZ + 8005;//jpa
    //    // 请先填写相关计划
    //    public static final int FILL_SOME_DATA_FIRST = SERVER_ERROR + BIZ + 8006;
    // 护理单元不存在护士长
    public static final int NURSE_UNIT_WITH_NO_MATRON = SERVER_ERROR + BIZ + 8007;//jpa
    //    //选择类型/方法不存在
    //    public static final int WITH_NO_METHOD = SERVER_ERROR + BIZ + 8008;
    //    //请先选择分析内容
    //    public static final int WITH_NO_ANALYSIS_CONTENT = SERVER_ERROR + BIZ + 8009;
    //    //请选择起止时间
    //    public static final int SELECT_BEGIN_END_TIME = SERVER_ERROR + BIZ + 8010;
    //    //请选择签入还是签出
    //    public static final int SIGN_IN_OR_OUT = SERVER_ERROR + BIZ + 8011;
    //    //改人员已经签入
    //    public static final int SIGN_IN_ALREADY = SERVER_ERROR + BIZ + 8012;
    //    //确认该课程是否需要签到
    //    public static final int NEED_OR_NOT_SIGN = SERVER_ERROR + BIZ + 8013;
    //    //请填写正确的配置项
    //    public static final int DICTITEM_VALUE_ERROR = SERVER_ERROR + BIZ + 8014;
    //    //请填写相应月份/季度
    //    public static final int FILL_SEASON_OR_MONTH = SERVER_ERROR + BIZ + 8015;
    //    //当前人没有督查小组
    //    public static final int NONE_INSPECTOR_GROUP = SERVER_ERROR + BIZ + 8016;
    /* BIZ 结束 */
    /* DB 开始 */
    //    // 科务会议保存失败
    //    public static final int NUMAS_MEETING_RECORD_SAVE_ERROR = SERVER_ERROR + DB + 8001;
    // 系统字典维护词条保存失败
    public static final int PLATFORM_CORE_WORD_SAVE_ERROR = SERVER_ERROR + DB + 8002; //jpa
    // 系统字典维护词义保存失败
    public static final int PLATFORM_CORE_DEFINITION_SAVE_ERROR = SERVER_ERROR + DB + 8003;//jpa
    // 护理单元添加失败
    public static final int PLT_NURSE_UNIT_SAVE_ERROR = SERVER_ERROR + DB + 8004;//jpa
    // 敏感指标已经上报不允许编辑
    public static final int SENSITIVE_INDEX_SAVE_ERROR = SERVER_ERROR + DB + 8005;//jpa
    // 至少选中一个护理单元
    public static final int BUSINESS_UNIT_MANAGEMENT_ERROR = SERVER_ERROR + DB + 8006;//jpa
    //    // 已经上报成功
    //    public static final int DATA_SUBMIT_SUCCESS = SERVER_ERROR + DB + 8007;
    //    // 字符数超过限制
    //    public static final int NO_LIMIT_WORD = SERVER_ERROR + DB + 8008;
    //    //已经添加这个护理单元的数据
    //    public static final int HAS_ALREADY_UNIT_DATA = SERVER_ERROR + DB + 8009;
    //标准菜单不允许删除
    public static final int INIT_NAV_MENU_NO_DELETE = SERVER_ERROR + DB + 8010;//jpa
    //编码唯一
    public static final int UNIQUE_CODE = SERVER_ERROR + DB + 8011;//jpa
    //名称唯一
    public static final int UNIQUE_NAME = SERVER_ERROR + DB + 8012;//jpa
    //    //敏感指标无可上报的内容
    //    public static final int SENSITIVE_INDEX_SUBMIT_NULL = SERVER_ERROR + DB + 8013;
    /* DB 结束 */

    /* 郑尚达 ***************************************************************************************/
    /* BIZ 开始 */
    //    // 护理单元不存在护士长
    //    public static final int PLT_NURSE_UNIT_NO_MATRON = SERVER_ERROR + BIZ + 9001;
    // 科室设置搜索结果为空
    public static final int PLT_DEPART_NO_RESULT = SERVER_ERROR + BIZ + 9002;//jpa
    //    // 质量小组搜索结果为空
    //    public static final int QOS_GROUP_NO_RESULT = SERVER_ERROR + BIZ + 9003;
    // 当前护理单元不属于任何科室
    public static final int PLT_DEPART_AUTH_NO_RESULT = SERVER_ERROR + BIZ + 9004;//jpa
    //    // 删除的质控小组有正在执行的质控任务
    //    public final static int QOS_GROUP_WITH_TASK = SERVER_ERROR + BIZ + 9005;
    //    // 在更新不良事件时，找不到对应的不良事件记录
    //    public final static int QOS_ADVERSE_EVENT_NO_EDIT_TARGET = SERVER_ERROR + BIZ + 9006;
    //    // 不良事件获取特殊事件信息失败
    //    public final static int QOS_ADVERSE_EVENT_GET_EXTRA_ERROR = SERVER_ERROR + BIZ + 9007;
    //    // 无法获取授权的护理单元
    //    public final static int QOS_SATIS_GET_AUTH_UNIT_ERROR = SERVER_ERROR + BIZ + 9008;
    //    // 不良事件风险指数根据id获取失败
    //    public final static int QOS_ADVERSE_EVENT_RISK_INDEX_GET_ERROR = SERVER_ERROR + BIZ + 9009;
    //    // 不良事件风险指数作用域重复
    //    public final static int QOS_ADVERSE_EVENT_RISK_INDEX_SCOPE_ERROR = SERVER_ERROR + BIZ + 9010;
    //    // 护理制度建设不允许当前用户下载
    //    public final static int QOS_DOC_DOWNLOAD_FORBID = SERVER_ERROR + BIZ + 9011;
    //    // 请选择有效的标准
    //    public final static int QOS_DOC_NO_CRITERION = SERVER_ERROR + BIZ + 9012;
    //    // 时间格式解析失败
    //    public final static int QOS_DATE_FORMAT_ERROR = SERVER_ERROR + BIZ + 9013;
    //    // 文书模板不存在
    //    public final static int NO_DOC_CONTENT = SERVER_ERROR + BIZ + 9014;
    //    // 标准导出失败
    //    public final static int QOS_DOC_CRI_EXPORT_ERROR = SERVER_ERROR + BIZ + 9015;
    //    // 标准导入失败
    //    public final static int QOS_DOC_CRI_IMPORT_ERROR = SERVER_ERROR + BIZ + 9016;
    //当前科室存在质控小组，删除失败
    public static final int PLT_DEPART_DELETE_ERROR = SERVER_ERROR + BIZ + 9017;//jpa
    //    //患者满意度删除失败，只有未提交的满意度调查才能被删除
    //    public static final int QOS_SICE_SAT_DELETE_ERROR = SERVER_ERROR + BIZ + 9018;
    //    //删除整改原因分类失败，需要先删除其下的所有原因内容
    //    public static final int REFORM_CATALOG_NOT_EMPTY = SERVER_ERROR + BIZ + 9019;
    //    //质量检查评级等级的否定优先级有重复
    //    public static final int QOS_RATING_LEVEL_WEIGHT_REPEAT = SERVER_ERROR + BIZ + 9020;
    //    //不良事件没有对应的质量检查追踪表
    //    public static final int QOS_EVENT_CRI_EMPTY = SERVER_ERROR + BIZ + 9021;
    //    //协助检查的记录没有检查所有标准
    //    public static final int QOS_MULTI_CHECK_NOT_ALL_CRI = SERVER_ERROR + BIZ + 9022;
    //    //发起护士满意度调查，删选人群为空
    //    public static final int QOS_NURSE_SATIS_NO_FILTER_USER = SERVER_ERROR + BIZ + 9023;
    //    //不良事件统计项导出失败
    //    public static final int CUSTOM_FIELD_EXPORT_ERROR = SERVER_ERROR + BIZ + 9024;
    //    //不良事件统计项导入失败
    //    public static final int CUSTOM_FIELD_IMPORT_ERROR = SERVER_ERROR + BIZ + 9025;
    //    //不良事件统计项正在使用，无法删除
    //    public static final int CUSTOM_FIELD_IN_USED = SERVER_ERROR + BIZ + 9026;
    //    //非开启人对质量检查记录进行完成检查操作
    //    public static final int TASK_RECORD_NOT_ALLOW_SAVE = SERVER_ERROR + BIZ + 9027;
    //    //不良事件流程业务范围有重叠
    //    public static final int ADVERSE_EVENT_PROCESS_BUSINESS_REPEAT = SERVER_ERROR + BIZ + 9028;
    //    //不良事件无对应的流程
    //    public static final int ADVERSE_EVENT_PROCESS_NO_MATCH = SERVER_ERROR + BIZ + 9029;
    //    //不良事件无对应的操作人
    //    public static final int ADVERSE_EVENT_OPERATOR_NO_MATCH = SERVER_ERROR + BIZ + 9030;

    /* BIZ 结束 */
    /* DB 开始 */
    //    // 不良事件保存失败
    //    public static final int QOS_ADVERSE_EVENT_SAVE_ERROR = SERVER_ERROR + DB + 9001;
    // 院区维护因名称或者编码重复导致保存失败
    public static final int PLT_HOS_AREA_REPEAT_KEY = SERVER_ERROR + DB + 9002;//jpa
    // 科室维护因编码重复导致保存失败
    public static final int PLT_DEPART_REPEAT_CODE = SERVER_ERROR + DB + 9003;//jpa
    // 科室维护因删除非叶结点导致删除失败
    public static final int PLT_DEPART_DEL_HAVE_CHILD = SERVER_ERROR + DB + 9004;//jpa
    //    // 质控小组名称重复
    //    public static final int QOS_GROUP_REPEAT_NAME = SERVER_ERROR + DB + 9005;
    //    // 标准名称重复
    //    public static final int QOS_CRI_REPEAT_NAME = SERVER_ERROR + DB + 9006;
    //    //不良事件追踪表配置重复
    //    public static final int QOS_ADVERSE_EVENT_CRI_REPEAT = SERVER_ERROR + DB + 9007;
    //    //查询超时
    //    public static final int QOS_SELECT_TIMEOUT = SERVER_ERROR + DB + 9008;
    //配置项缺失
    public static final int PLT_DICT_EMPTY = SERVER_ERROR + DB + 9009;//jpa
    /* DB 结束 */

    //message模块
    //消息推送请求参数错误
    public static final int MESSAGE_REQUEST_PARAM_ERROR = SERVER_ERROR + BIZ + 200;//jpa
    //通知推送请求参数错误
    public static final int MESSAGE_NOTIFY_REQUEST_PARAM_ERROR = SERVER_ERROR + BIZ + 201;//jpa
    //消息推送错误
    public static final int MESSAGE_PUSH_ERROR = SERVER_ERROR + BIZ + 202;//jpa
    //    //护士长手册
    //    //重复上报
    //    public static final int MANUAL_REPEAT_SUBMIT = SERVER_ERROR + BIZ + 400;
    //    //上报状态不匹配
    //    public static final int MANUAL_SUBMIT_MISMATCH = SERVER_ERROR + BIZ + 401;
    //    //无权限审批
    //    public static final int MANUAL_CHECK_NOAUTHORITY = SERVER_ERROR + BIZ + 402;
    //    //非本人无法撤销
    //    public static final int MANUAL_CANCEL_NOAUTHORITY = SERVER_ERROR + BIZ + 403;


}