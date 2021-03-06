#language: zh-CN
@testPaidRechargeOrderCancel
@lanchaoTest
功能:未发货余额付款订单取消订单
  场景: 总仓发货组合商品余额购买并付款，取消订单
    假如有门店,门店有店长
    并且随机创建姓名为李姑娘的客户
    并且客户充值35000元
    #当客户下单组合商品"惠享金秋"ZH02B134136T619892, M216C239C0458(长袖腰背夹M216C239, 深蓝色, 58), M216D243C0458(长塑裤M216D243, 深蓝色, 58), 总仓1个, 余额支付方式, 未付款
    当客户余额下单总仓发货组合商品惠享金秋
    那么预期总览销售总额增加5001元
    并且预期商品ZH02B134136T619892总仓库存增加-1
    并且预期商品M216C239C0458总仓库存增加-1
    并且预期商品M216D243C0458总仓库存增加-1
    并且预期省运营中心销售额增加5001元
    并且预期运营中心销售额增加5001元
    并且预期门店销售额增加5001元
    并且预期商品M216C239C0458门店原始仓库存增加0
    并且预期商品M216D243C0458门店原始仓库存增加0
    并且预期商品M216C239C0458门店标准仓库存增加0
    并且预期商品M216D243C0458门店标准仓库存增加0
    并且预期店长业绩增加5001元
    并且预期客户的卡级别为银卡,积分增加0,消费额增加5001元,余额增加-5001元
    那么验证预期值正常
    当客户取消订单并缓存数据
    那么预期总览销售总额增加-5001元
    并且预期商品ZH02B134136T619892总仓库存增加1
    并且预期商品M216C239C0458总仓库存增加1
    并且预期商品M216D243C0458总仓库存增加1
    并且预期省运营中心销售额增加-5001元
    并且预期运营中心销售额增加-5001元
    并且预期门店销售额增加-5001元
    并且预期商品M216C239C0458门店原始仓库存增加0
    并且预期商品M216D243C0458门店原始仓库存增加0
    并且预期商品M216C239C0458门店标准仓库存增加0
    并且预期商品M216D243C0458门店标准仓库存增加0
    并且预期店长业绩增加-5001元
    并且预期客户的卡级别为银卡,积分增加0,消费额增加-5001元,余额增加5001元
    那么验证预期值正常
