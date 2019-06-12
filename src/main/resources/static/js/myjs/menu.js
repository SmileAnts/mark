$(function() {
	layui.use('table', function() {
		var table = layui.table;
		table.render({
			elem : '#menu',
			height : "full-20",
			url : '/menu/selectAll',
			toolbar : '#toolbarDemo',
			page : {
				theme : '#009688'
			},
			limits : [ 2, 5, 10 ],
			limit : 2,
			parseData : function(res) {
				console.log(res)
				return {
					"data" : res.data,
					"code" : res.code,
					"count" : res.count
				};
			},
			response : {
				statusCode : 200
			},
			cols : [ [ // 表头
			{
				checkbox : true,
				width : 60,
				fixed : true
			}, , {
				field : 'id',
				title : 'ID',
				fixed : 'left',
				hide : true,
			}, {
				field : 'name',
				title : '名称',
			}, {
				field : 'url',
				title : '链接',
			} ] ]
		});

		// 监听事件
		table.on('toolbar(test)', function(obj) {
			var checkStatus = table.checkStatus(obj.config.id);
			switch (obj.event) {
			case 'add':
				var checkStatus = table.checkStatus('menu');
				console.log(checkStatus)
				layer.open({
					type : 2,
					content : '/add/add_menu.html' // 这里content是一个普通的String
				});
				break;
			case 'delete':
				layer.msg('删除');
				break;
			case 'update':
				layer.msg('编辑');
				break;
			}
			;
		});
	});

	layui.use('form', function() {
		var form = layui.form;

		// 监听提交
		form.on('submit(formDemo)', function(data) {
			layer.msg(JSON.stringify(data.field));
			return false;
		});
	});
})
