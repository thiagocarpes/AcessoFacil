function validaForm(form){

	var erro = 0;
	$('[id^="avaliacao_"]').each(function(){
		
		if($(this).val() == ""){
			alert("clique sobre as estrelas das questões para avaliar");
			erro = 1;
			return false;
		}
		
	});
	
	if($('textarea[name="comentario"]').val() == ""){
		alert("Preencha o comentário");
		erro = 1;
	}	
	
	if(!erro){
		return true;
	}else{
		return false;
	}	
	
}

function fm(form) {
    var erro = false;
    $(form + ' input').each(function () {
        var content = $(this).val();
        var exclude = $(this).attr('validate');
        $(this).attr("style", "");
        if (content == "" && exclude == "1") {
            alert("Erro: Campo obrigatorio");
            $(this).css({
                'border': '1px solid #f40000'
            });
            $(this).focus();
            erro = 1;
            return false;
        }
    });
    if (erro) return false;
    $(form + ' input[type="email"]').each(function () {
        var content = $(this).val();
        var exclude = $(this).attr('validate');
        var emailError = 0;
        var emailFilter = /^.+@.+\..{2,}$/;
        var illegalChars = /[\(\)\<\>\,\;\:\\\/\"\[\]]/;
        if (!(emailFilter.test(content)) || content.match(illegalChars)) {
            emailError = 1;
        }
        $(this).attr("style", "");
        if ((content == "" || emailError == 1) && exclude == "1") {
            alert("Erro: Email Invalido");
            $(this).css({
                'border': '1px solid #f40000'
            });
            $(this).focus();
            erro = 1;
            return false;
        }
    });
    if (erro) return false;
    $(form + ' textarea').each(function () {
        var content = $(this).val();
        var exclude = $(this).attr('validate');
        $(this).attr("style", "");
        if (content == "" && exclude == "1") {
            alert("Erro: Campo obrigatorio");
            $(this).css({
                'border': '1px solid #f40000'
            });
            $(this).focus();
            erro = 1;
            return false;
        }
    });
    if (erro) return false;
    $(form + ' select').each(function () {
        var content = $(this).val();
        var exclude = $(this).attr('validate');
        $(this).attr("style", "");
        if (content == "" && exclude == "1") {
            alert("Erro: Campo obrigatorio");
            $(this).css({
                'border': '1px solid #f40000'
            });
            $(this).focus();
            erro = 1;
            return false;
        }
    });
    if (erro) return false;
    $(form + ' input[name="cosenha"]').each(function () {
        var senha = $(form + ' input[name="senha"]').val();
    	var content = $(this).val();
        var exclude = $(this).attr('validate');
        $(this).attr("style", "");
        if (content != senha && exclude == "1") {
            alert("Erro: as senhas nao sao iguais");
            $(this).css({
                'border': '1px solid #f40000'
            });
            $(this).focus();
            erro = 1;
            return false;
        }

        if(!userPassword(senha)){
        	erro = 1;
        	alert("A senha deve conter no minimo 6 digitos e no maximo 10, deve conter no minimo uma letra e um numero");
        }
    	
    });
    if (erro) return false;
    if (!erro) {
        $(form).submit();
        return true;
    }
}

function userPassword(str){
	
	var senha = str;
	var letras = false;
	var numeros = false;
	
	if(senha.length < 6 || senha.length > 10){
	    return false;
	}
	
	if(/[a-z]/gm.test(senha)){
		letras = true;
	}
	
	if(/[0-9]/gm.test(senha)){
		numeros = true;
	}
	
	if(letras && numeros){
		return true;
	}else{
		return false;
	}
	
}

function avaliarPost(kw){
	$.post('getEstab', {
		kw: kw
    }, function (data) {
    	$('#returnSearch').hide();
    	
    	if(data != "" ){
    		$('#returnSearch').html(data);
    		$('#returnSearch').fadeIn();    		
    	}
    });
}

$(document).ready(function () {
	
	$('#avaliarPost').keyup(function(){
		avaliarPost($(this).val());
	});
	
	var screen = $(window).height();
	$('.banner').css({ 'height' : (screen - 220) + 'px'});
	
	$("input[name='cep']").mask("99999-999", {
        completed: function () {
            $('input[name="endereco"]').val("Carregando, aguarde...");
            $.post('getAdress', {
                cep: $("input[name='cep']").val()
            }, function (data) {
                
            	console.log(data);
                if(!data.erro){
                    $("input[name='endereco']").val(data.logradouro);
                    $("input[name='bairro']").val(data.bairro);
                    $("input[name='cidade']").val(data.localidade);
                    $("#estado option[value="+data.uf+"]").attr('selected','selected');
                    $("input[name='numero']").focus();
                }else{
                	$('input[name="endereco"]').val("");
                	$("input[name='endereco']").val("");
                    $("input[name='bairro']").val("");
                    $("input[name='cidade']").val("");
                    $("#estado option[value='#']").attr('selected','selected');
                	alert('Cep nao localizado');
                }

            });
        }
    });
	
	$('.group_star span.glyphicon-star').each(function(){
		
		$(this).click(function(){
			
			var star = $(this).data('star');
			var number = $(this).parent().attr("data-idGroup");
			console.log(star + "=" +number);
			
			$("div[data-idGroup='"+number+"'] span.glyphicon-star").each(function(){
				$("#avaliacao_"+number).val(star);
				if(star < $(this).data('star')){
					$(this).css({'color':'#d2d2d2'});
				}else{
					$(this).css({'color':'#f4d300'});
				}
				
			});
			
		});

		
	});
	
});