package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.manager.service.ItemService;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.utils.PageBeanResult;

@Controller
public class ItemController {

	// @Resource(name="itemService")
	@Autowired
	private ItemService itemService;

	/**
	 * 需求:根据id查询数据 请求:item/findItem/635906 参数:Long itemId 返回值:json格式TbItem
	 */

	@RequestMapping("item/findItem/{itemId}")
	@ResponseBody
	public TbItem findItemById(@PathVariable Long itemId) {
		TbItem item = itemService.findItemById(itemId);
		return item;
	}

	/**
	 * 需求:分页查询商品列表 参数:Integer page , Integer rows 返回值:json格式PageBeanResult
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public PageBeanResult itemList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "20") Integer rows) {
		// 调用远程service服务方法
		PageBeanResult result = itemService.findItemByPage(page, rows);
		return result;

	}

}