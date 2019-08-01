var table = layui.table;
var form = layui.form;
// 头工具栏事件
table.on('toolbar(photoCode)', function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		$("#reset").css("display", "")
		form.val("form", {
				"name" : '',
				"id" : '',
			})
		var index = layer.open({
			type : 1,
			title : '新增用户',
			content : $('#form'),
			area : [ '500px']
		});
		$("#index").val(index)
		break;
	case 'del':
		if (checkStatus.data.length == 0){
			layer.msg('请选中一条数据');
			return;
		}
		layer.confirm('删除菜单?',{btn: ['确定', '取消'], title: "提示"}, function(index){
			var param = checkStatus.data
			var p = []
			param.forEach(pa => {
				p.push(pa.id)
			})
			$.ajax({
				url : '/code/delete',
				data : {
					ids : p + ''
				},
				success : function(result) {
					layer.close(index);
					reload(result.code == 200)
				}
			})
		});
		break;
	case 'create':
		layer.confirm('生成二维码?',{btn: ['确定', '取消'], title: "提示"}, function(index){
			$.ajax({
				url : '/code/create',
				success : function(result) {
					layer.close(index)
					layer.open({
						title : '二维码路径',
						content : result
					})
				}
			})
		});
		break;
	}
	;
});

form.on('submit(*)', function(data, index) {
	$.ajax({
		url : '/code/add',
		data : data.field,
		success : function(result) {
			console.log(result)
			reload(result.code == 200)
		}
	})
	layer.close($("#index").val());
	return false;
});

/**
 * 重载table
 * 
 * @param status
 * @returns
 */
function reload(status){
	console.log(status)
	if(status){
		table.reload('photoCode', {
			url : '/code/query'
		})
	}
}