$(document).ready(function() {/* jQuery toggle layout */
	$('#btnToggle').click(function() {
		if ($(this).hasClass('on')) {
			$('#main .col-md-6').addClass('col-md-4').removeClass('col-md-6');
			$(this).removeClass('on');
		} else {
			$('#main .col-md-4').addClass('col-md-6').removeClass('col-md-4');
			$(this).addClass('on');
		}
	});
});

$(".answerWrite button[type='button']").on("click", addAnswer);

function addAnswer(e) {
	e.preventDefault();
	var queryString = $("form[name=answer]").serialize();
	var url = $(".answerWrite").attr("action");
	
	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess,
	});
}

function onSuccess(json, status) {
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(json.writer,
			new Date(json.createdDate), json.contents, json.answerId,
			json.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
}

function onError(xhr, status) {
	alert("error");
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};

$(".qna-comment-slipp-articles").on("click", ".form-delete button[type='button']", deleteAnswer);


function deleteAnswer(e) {
	e.preventDefault();
	var deleteBtn = $(this);
	var url = deleteBtn.closest('form').attr("action");
	
	$.ajax({
		type : 'post',
		url : url,
		dataType : 'json',
		error : function(xhr, status) {
			alert("error");
		},
		success : function(json, status) {
			if (json.status) {
				deleteBtn.closest('article').remove();
			}
		}
	});
}