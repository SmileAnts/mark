var table = layui.table;
var form = layui.form;
// 头工具栏事件
table.on('toolbar(menu)', function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		$("#reset").css("display", "")
		form.val("userForm", {
				"name" : '',
				"id" : '',
				"url" : '',
				"sort" : ''
			})
		var index = layer.open({
			type : 1,
			title : '新增用户',
			content : $('#form'),
			area : [ '500px' ]
		});
		$("#index").val(index)
		break;
	case 'edit':
		var data = checkStatus.data;
		if (data.length == 1) {
			form.val("userForm", {
				"name" : data[0].name,
				"id" : data[0].id,
				"url" : data[0].url,
				"sort" : data[0].sort
			})
			$("#reset").css("display", "none")
			var index = layer.open({
				type : 1,
				title : '用户编辑',
				content : $('#form'),
				area : [ '500px' ]
			});
			$("#index").val(index)
		} else {
			layer.msg('请选中一条数据');
		}
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
				url : '/menu/delete',
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

function reload(status){
	console.log(status)
	if(status){
		table.reload('menu', {
			url : '/menu/query'
		})
		table.reload('child', {
			url : '/menu/parent?parentId=' + $("#parentId").val()
		})
	}
}

form.on('submit(*)', function(data, index) {
	$.ajax({
		url : '/menu/add',
		data : data.field,
		success : function(result) {
			reload(result.code == 200)
		}
	})
	layer.close($("#index").val());
	return false;
});
table.on('toolbar(child)', function(obj) {
	console.log(obj)
	var checkStatus = table.checkStatus(obj.config.id);
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		$("#reset").css("display", "")
		form.val("childForm", {
				"name" : '',
				"id" : '',
				"url" : '',
				"sort" : ''
			})
		var index = layer.open({
			type : 1,
			title : '新增用户',
			content : $('#formChild'),
			area : [ '500px' ]
		});
		$("#index").val(index)
		break;
	case 'edit':
		var data = checkStatus.data;
		if (data.length == 1) {
			form.val("childForm", {
				"name" : data[0].name,
				"id" : data[0].id,
				"url" : data[0].url,
				"sort" : data[0].sort
			})
			$("#reset").css("display", "none")
			var index = layer.open({
				type : 1,
				title : '用户编辑',
				content : $('#formChild'),
				area : [ '500px' ]
			});
			$("#index").val(index)
		} else {
			layer.msg('请选中一条数据');
		}
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
				url : '/menu/delete',
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


// 监听行双击事件
table.on('rowDouble(menu)', function(obj){
	table.render({
	    elem: '#child'
	    ,height: 500
	    ,data: obj.data.children // 数据接口
	    ,page: true // 开启分页
	    ,toolbar: '#barDemo' 
	    ,defaultToolbar:[]
	    ,cols: [[
	      {type: 'checkbox'},
	      {field: 'id', title: 'ID', width:80, sort: true},
	      {field: 'name', title: '菜单名称', width:120},
	      {field: 'url', title: '路由', width:120},
	      {field: 'createTime', title: '创建时间', width:100},
	      {field: 'sort', title: '排序', width:60}
	    ]]
	  });
	$("#parentId").val(obj.data.id);
	$("#parentName").val(obj.data.name);
	layer.open({
		type : 1,
		title : '子菜单',
		content : $('#child_div'),
		area : [ '555px' ]
	});
});