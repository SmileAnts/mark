var table = layui.table
var form = layui.form
var doubbleClick = true
var tree = layui.tree
// 头工具栏事件
table.on('toolbar(role)', function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		layer.open({
			type : 1,
			title : '新增用户',
			content : $('#form'),
			area : [ '500px' ]
		});
		break;
	case 'edit':
		
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

// 监听行双击事件
table.on('rowDouble(role)', function(obj){
});

function save (){
	var checkedData = tree.getChecked('menu'); // 获取选中节点的数据
	console.log(checkedData)
}