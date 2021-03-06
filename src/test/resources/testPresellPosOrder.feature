#language: zh-CN
@testPresellPosOrder
功能:预售商品测试
# failed
  场景: 预售商品购买,pos支付
    假如有门店,门店有店长
    并且随机创建姓名为哈女士的客户
    并且设置预售商品不发售
    并且缓存操作前数据
    当客户下单购买预售商品
    那么缓存操作后数据
    并且预期总览销售总额增加0元
    并且预期商品M116A227B01_A65总仓库存增加-5
    并且预期省运营中心销售额增加18216元
    并且预期运营中心销售额增加18216元
    并且预期门店销售额增加18216元
    并且预期商品M116A227B01_A65门店库存增加-5
    并且预期店长业绩增加18216元
    并且预期客户的卡级别为金卡,积分增加18216,消费额增加18216元,余额增加0元
    那么验证预期值正常
    当设置预售商品发售
    并且缓存操作前数据
    并且预售商品发货
    那么缓存操作后数据
    并且预期总览销售总额增加0元
    并且预期商品M116A227B01_A65总仓库存增加-5
    并且预期省运营中心销售额增加18216元
    并且预期运营中心销售额增加18216元
    并且预期门店销售额增加18216元
    并且预期商品M116A227B01_A65门店库存增加-5
    并且预期店长业绩增加18216元
    并且预期客户的卡级别为金卡,积分增加18216,消费额增加18216元,余额增加0元
    那么验证预期值正常
