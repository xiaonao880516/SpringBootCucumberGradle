#language: zh-CN
@testStoreRepository
@lanchaoTest
功能:验证门店补仓及门店退货

  背景:
    假如有门店,门店有店长
#    并且缓存操作前数据

  场景:门店申请补仓
    当门店申请补仓商品M116A227B01_A65,5个
    那么预期商品M116A227B01_A65门店标准仓库存增加5
    并且验证预期值正常

  场景:门店申请退货
    当门店申请退货商品M116A227B01_A65,5个
    那么预期商品M116A227B01_A65门店标准仓库存增加-5
    并且验证预期值正常