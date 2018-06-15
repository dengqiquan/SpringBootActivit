function addOrEdit(id){
	var url = "edit";
	if(id!=null){
		url = url + "?id="+id;
	}
	layer.open({
		content: url, //iframe的url  
		type: 2,
		title: '添加/修改',
	    shade: 0.8,
	    area: ['100%', '100%'],
	    btn: ['确定', '取消'],
	    yes: function(index, layero){
		  var iframeWin = window[layero.find('iframe')[0]['name']];
		  iframeWin.save(index);
	  	},cancel: function(index){}
	});
}

/**
 * 调用分页方法
 * @param number 当前页
 * @param count  总条数
 * @param paginationDivID 分页divID
 * @param searchFormId 列表查询form标签ID
 * @param inputPageNumberName form中pageNumber的input标签的name
 */
function paginationFn(number,count,paginationDivID){
	if(count>0){
		$('#'+paginationDivID).pagination({
			pages: count,
			cssStyle: 'light-theme',
			currentPage:number,
			onPageClick:function(pageNumber, event){
				$("input[name='pageNumber']").val(pageNumber);
				$('#searchForm').submit();
			}
		});	
	}
}

$(function(){
	var pageNumber = $("#pageNumber").val();
	var pageCount = $("#pageCount").val();
	paginationFn(pageNumber,pageCount,"light-pagination");
})