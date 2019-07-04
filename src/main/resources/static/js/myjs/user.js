var table = layui.table
var form = layui.form
// 头工具栏事件
table.on('toolbar(user)', function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		$("#password").css("display", "")
		$("#reset").css("display", "")
		layer.open({
			type : 1,
			title : '新增用户',
			content : $('#form'),
			area : [ '500px' ]
		});
		break;
	case 'edit':
		var data = checkStatus.data;
		if (data.length == 1) {
			form.val("userForm", {
				"username" : data[0].username,
				"id" : data[0].id,
				"password" : data[0].password
			})
			$("#password").css("display", "none")
			$("#reset").css("display", "none")
			layer.open({
				type : 1,
				title : '用户编辑',
				content : $('#form'),
				area : [ '500px' ]
			});
		} else {
			layer.msg('请选中一条数据');
		}
		break;
	case 'del':
		if (checkStatus.data.length == 0){
			layer.msg('请选中一条数据');
			return;
		}
		layer.confirm('删除用户?',{btn: ['确定', '取消'], title: "提示"}, function(index){
			var param = checkStatus.data
			var p = []
			param.forEach(pa => {
				p.push(pa.id)
			})
			$.ajax({
				url : '/user/delete',
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
	}
	;
});

form.on('submit(*)', function(data) {
	$.ajax({
		url : '/user/register',
		data : data.field,
		success : function(result) {
			reload(result.code == 200)
		}
	})
});

function reload(status){
	if(status){
		table.reload('user', {
			url : '/user/list'
		})
	}
}

// 监听行双击事件
table.on('rowDouble(user)', function(obj){
  console.log(obj)
});