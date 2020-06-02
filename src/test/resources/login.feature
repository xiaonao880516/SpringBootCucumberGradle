#language: zh-CN
#"zh-CN": {
#  "but": "*|但是<",
#  "and": "*|而且<|并且<|同时<",
#  "then": "*|那么<",
#  "when": "*|当<",
#  "name": "Chinese simplified",
#  "native": "简体中文",
#  "feature": "功能",
#  "background": "背景",
#  "scenario": "场景|剧本",
#  "scenario_outline": "场景大纲|剧本大纲",
#  "examples": "例子",
#  "given": "*|假如<|假设<|假定<"
# }
@test
功能:前台员工登录，后台系统用户登录

  @THooks
  场景大纲:前台登录，场景大纲，例子测试
    假设有员工id是："<id>" ，姓名是： "<name>" ，密码是： "<password>" ，手机号是： "<phone_number>"
    例子:
      | id  | name | password | phone_number |
      | 285 | 孟伟   | 123456ba | 13810567325  |
      | 385 | 孟伟联通 | 123456ba | 17611267582  |

  场景: 前台登录，数据表测试
    假设有以下员工列表
  | id  | name | password | phone |
  | 285 | 孟伟   | 123456ba | 13810567325  |
  | 385 | 孟伟联通 | 123456ba | 17611267582  |
    当所有员工前台登录
    那么所有员工登录成功


  场景: 后台系统用户登录
    假设有系统用户，用户名是：mengwei ，密码是：mengwei123
    当系统用户后台登录
    那么系统用户后台登录成功
