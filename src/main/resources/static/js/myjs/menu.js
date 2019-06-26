layui.use('tree', function() {
	var tree = layui.tree;
	var node = [];
	$.ajax({
	  url: "/menu/query",
	  success : function(result){
		  treeData(result.data, node)
		  tree.render({
			  elem : '#menu',
			  edit: ['update', 'del', 'add'],
			  showCheckbox : true,
			  id : 'menu',
			  accordion : true,
			  data : node
		  });
		  $('.layui-tree-btnGroup').css('visibility', 'hidden');
		  $('.layui-tree-entry').bind('mouseover', function(){
				$($(this)[0].lastElementChild).css('visibility', "visible")
		  })
		   $('.layui-tree-entry').bind('mouseout', function(){
				$($(this)[0].lastElementChild).css('visibility', "hidden")
		  })
		  console.log(node)
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
