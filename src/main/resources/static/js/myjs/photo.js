var table = layui.table;
var form = layui.form;
var upload = layui.upload;
// 头工具栏事件
table.on('toolbar(photo)', function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
	switch (obj.event) {
	case 'add':
		var data = checkStatus.data;
		var index = layer.open({
			type : 1,
			title : '新增用户',
			content : $('#form'),
			area : [ '800px']
		});
		$("#select").html("");
		$.ajax({
			url : '/code/query',
			success : function (result){
				result.data.forEach(photoCode => {
					 $("#select").append("<option value="+photoCode.id+">"+photoCode.name+"</option>")
				})
			}
		})
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
				url : '/photo/delete',
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
form.on('submit(*)', function(data, index) {
	$.ajax({
		url : '/photo/add',
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
		table.reload('photo', {
			url : '/photo/query'
		})
	}
}

// 图片上传
// 多图片上传
upload.render({
  elem: '#test2'
  ,url: '/upload/file'
  ,multiple: false
  ,before: function(obj){
    // 预读本地文件示例，不支持ie8
    obj.preview(function(index, file, result){
      $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
    });
  }
  ,done: function(res){
    // 上传完毕
	  console.log(res)
	  $("#url").val(res.data)
  }
});
