var table = layui.table
var form = layui.form
// 头工具栏事件
table.on('toolbar(user)', function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
	console.log(checkStatus)
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		$("#password").css("display", "")
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
	case 'delete':
		layer.msg(checkStatus.isAll ? '全选' : '未全选');
		break;
	}
	;
});

form.on('submit(*)', function(data) {
	$.ajax({
		url : '/user/register',
		data : data.field,
		success : function(result) {
			console.log(result)
			if (result.code == 200) {
				table.reload('user', {
					url : '/user/list'
				})
			}
		}
	})
});

table.render({
	elem : '#user',
	url : '/user/list',
	cols : [ [ {
		type : 'checkbox'
	}, {
		field : 'id',
		title : 'ID',
		width : 200,
		templet : function (d){
			console.log(d.id)
			return '<span style="color: #c00;">'+ String.valueOf(d.id )+'</span>'
		}
	} ] ]

});
