package cn.e3mall.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.manager.service.ItemService;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.utils.PageBeanResult;

@Service("itemService")
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	
	public TbItem findItemById(Long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}
	
	/**
 	 * 需求:分页查询商品列表
 	 * 参数:Integer page , Integer rows
 	 * 返回值:PageBeanResult
 	 * 分页方法:使用pageHelper插件分页
 	 * 习惯:检查服务是否发布
 	 */
	public PageBeanResult findItemByPage(Integer page, Integer rows) {
		//查询,创建Example对象
		TbItemExample example = new TbItemExample();
		
		//查询之前,必须先进行分页
		PageHelper.startPage(page, rows);
		
		//执行查询,自动分页查询,自动生成limit,count
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//创建PageInfo对象,封装分页数据
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		//创建分页返回值包装类
		PageBeanResult result = new PageBeanResult();
		
		
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		return result;
	}
	

}
