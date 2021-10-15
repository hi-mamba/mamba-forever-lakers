package space.mamba.secondskill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.mamba.secondskill.dao.StockMapper;
import space.mamba.secondskill.pojo.Stock;
import space.mamba.secondskill.service.api.StockService;

import javax.annotation.Resource;

/**
 * @auther G.Fukang
 * @date 6/7 12:45
 */
@Service(value = "StockService")
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Override
    public int getStockCount(int id) {
        Stock stock = stockMapper.selectByPrimaryKey(id);
        return stock.getCount();
    }

    @Override
    public Stock getStockById(int id) {

        return stockMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStockById(Stock stock) {

        return stockMapper.updateByPrimaryKeySelective(stock);
    }

    @Override
    public int updateStockByOptimistic(Stock stock) {

        return stockMapper.updateByOptimistic(stock);
    }

    @Override
    public int initDBBefore() {
        //新增一条记录为1的
        return stockMapper.initDBBefore();
    }
}
