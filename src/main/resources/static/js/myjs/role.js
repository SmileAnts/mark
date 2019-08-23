var table = layui.table
var form = layui.form
var doubbleClick = true
var tree = layui.tree
var roleId = []
// 头工具栏事件
table.on('toolbar(role)', function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		layer.open({
			type : 1,
			title : '新增角色',
			content : $('#form'),
			area : [ '500px' ]
		});
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
				url : '/role/delete',
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
		url : '/role/add',
		data : data.field,
		success : function(result) {
			reload(result.code == 200)
		}
	})
});

function reload(status){
	if(status){
		table.reload('role', {
			url : '/role/list'
		})
	}
}

function save (){
	var checkedData = tree.getChecked('menu'); // 获取选中节点的数据
	var menuIds = []
	checkedData.forEach(i => {
		menuIds.push(i.id)
	})
	console.log(menuIds.join(','))
	console.log(roleId.join(','))
	$.ajax({
		url: '/role/setMenuRole',
		data:{
			roleIds : roleId.join(','),
			menuIds : menuIds.join(',')
		},
		success:function(result){
			console.log(result)
		}
	})
}
// 选中角色事件
table.on('checkbox(role)', function(obj){
	if(obj.checked){
		roleId.push(obj.data.id)
	} else {
		var index = roleId.indexOf(obj.data.id)
		roleId.splice(index,1) 
	}
	if(roleId.length > 0){
		$("#save").css("display","")
	} else {
		$("#save").css("display","none")
	}
});

// 下边为menu
layui.use('tree', function() {
	var tree = layui.tree;
	var node = [];
	$.ajax({
	  url: "/menu/query",
	  success : function(result){
		  treeData(result.data, node)
		  tree.render({
			  elem : '#menu',
			  showCheckbox : true,
			  id : 'menu',
			  accordion : false,
			  data : node
		  });
	  }
	})
});

function treeData(datas, array){
	datas.forEach(data =>{
		var index = datas.indexOf(data)
		array[index] = {
			'title' : data.name == null ? data.title : data.name,
			'id' : data.id,
			'checked' : false,
			'spread' : false
		}
		if(data.children && data.children.length > 0){
			array[index].children = data.children
			data.children.forEach(child => {
				var childIndex = data.children.indexOf(child)
				array[index].children[childIndex] = {
					'title' : child.name,
					'id' : child.id,
					'checked' : false
				}
			})
			treeData(datas[index].children, array[index].children)
		}
	})
}