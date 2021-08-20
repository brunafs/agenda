function listar() {		
	fetch('http://localhost:8081/Basico/rs/contatos')
	.then(response => response.json())
        .then(dados => {
            dados.forEach(contato => {
				let nome = contato.nome;
				let telefone = contato.telefone;
				let email = contato.email;				
				let dataNascimento = contato.dataNascimento;
                let lista = document.getElementById('MinhaTabela');

				let tr = document.createElement('tr');
				
				let tdCol6 = tr.appendChild(document.createElement('td'));
				tdCol6.innerHTML = '<img style="height: 40px; border-radius: 30px;" src="data:image/*;base64, ' + contato.foto + '" />';
				
    			let tdCol1 = tr.appendChild(document.createElement('td'));
				tdCol1.innerHTML = nome;
				
				let tdCol2 = tr.appendChild(document.createElement('td'));
				tdCol2.innerHTML = telefone;
				
				let tdCol3 = tr.appendChild(document.createElement('td'));
				tdCol3.innerHTML = email;
				
				let tdCol4 = tr.appendChild(document.createElement('td'));
				tdCol4.innerHTML = timeStampToDate(contato.dataNascimento);;
				
				
				let tdCol5 = document.createElement('td');	
							
				let btnEditar = document.createElement('button');
                tdCol5.appendChild(btnEditar);
                btnEditar.setAttribute('class', "btn btn-info btn-sm btnEditar");
				btnEditar.innerHTML = "Editar";
                btnEditar.setAttribute('onclick', "busca(" + contato.id + ")");	

				let btnExcluir = document.createElement('button');
				tdCol5.appendChild(btnExcluir);
                btnExcluir.setAttribute('class', "btn btn-danger btn-sm");
				btnExcluir.innerHTML = "Excluir";
				btnExcluir.setAttribute('onclick', "deletarContato(" + contato.id + ")");

				tr.appendChild(tdCol5);
												
                lista.appendChild(tr);
			}); 
		})	
}

function timeStampToDate(timestamp) {
    if (timestamp) {
        var date = new Date(timestamp);
        return date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
    } else {
        return "";
    }
}

function novoContato() {
	event.preventDefault();
	let id = document.getElementById('id').value;
	let nome = document.getElementById('nome').value;		
    let nomeCompleto = document.getElementById('nomeCompleto').value;
    let email = document.getElementById('email').value;
	let telefone = document.getElementById('telefone').value;
	let idGrupo = document.getElementById('selectGrupos').value;
	let dataNascimento = document.getElementById('dataNascimento').value;
	let foto = document.getElementById('avatar').files[0];
	
    const contatoAdd = {
        id: null,
        nome: nome,
        nomeCompleto: nomeCompleto,
		email: email,
		telefone: telefone,
		idGrupo: idGrupo,
		dataNascimento: dataNascimento,
		foto: null,
		fotoNome: foto ? foto.name : null,
        fotoTamanho: foto ? foto.size : null,
        fotoTipo: foto ? foto.type : null,
    }
	var validar = validacao(contatoAdd);
	
	let formData = new FormData();
	formData.append("file", foto)
	formData.append("contato", JSON.stringify(contatoAdd))
	
	if (validar) {
		fetch('http://localhost:8081/Basico/rs/contatos', {
	        method: 'POST',
	        body: formData
	    }).then(response => {
			alert("Contato adicionado com sucesso!");
	        window.location = '/Basico/index.html';
	    });
	}
}

function deletarContato(id){
	var confirmar = confirm("Confirmar exclusão do contato")
	if (confirmar) {		
		let modalMensagem = true;
		fetch('http://localhost:8081/Basico/rs/contatos/' + id, {
		method: 'DELETE'
	}).then(response => {
			alert("Contato excluído com sucesso!");
	        window.location = '/Basico/index.html';			
	    });;
	}	
}

function selectGrupos(){
	fetch('http://localhost:8081/Basico/rs/grupos')
	.then(response => response.json())
        .then(dados => {
            dados.forEach(grupo => {			
				let campo = document.getElementById('selectGrupos');
				
				var option = document.createElement('option');
				option.setAttribute('class', "optionGrupos");
				
				option.setAttribute('onclick', () => {
					option.setAttribute('selected', 'true');
					document.getElementById('selectGrupos').setAttribute('value', grupo.id);	
				});
				
				option.innerHTML = grupo.nome;
				option.setAttribute('value', grupo.id);
				campo.appendChild(option);
			}); 
		})
}

function busca(id){
	window.location = "/Basico/pages/cadastrarContato.html?id=" + id;
}

function dataEditar(timestamp) {
    var date = new Date(timestamp);
    return date.getFullYear() + "-" + ((date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : '0' + (date.getMonth() + 1)) + "-" + ((date.getDate()) > 9 ? (date.getDate()) : '0' + (date.getDate()));

}

function verifica() {
	selectGrupos();
	const url = new URLSearchParams(window.location.search);
    const id = url.get("id");
    if (id != null) {		
		fetch('http://localhost:8081/Basico/rs/contatos/' + id)
		.then(response => response.json())
    	    .then(contato => {
				console.log(contato)
				document.getElementById('btnSalvar').setAttribute('hidden', "true");
				document.getElementById('btnEditar').removeAttribute('hidden');	
				document.getElementById('nome').setAttribute('value', contato.nome);
				document.getElementById('nomeCompleto').setAttribute('value', contato.nomeCompleto);
				document.getElementById('email').setAttribute('value', contato.email);
				document.getElementById('telefone').setAttribute('value', contato.telefone);
				document.getElementById('dataNascimento').value = dataEditar(contato.dataNascimento);
				
				divFoto = document.getElementById('foto');
				let foto = document.createElement('img');
				foto.setAttribute("src", "data:image/*;base64, " + contato.foto);
				foto.setAttribute("style", "height: 90px; border-radius: 10px; padding: 10px;");				
				divFoto.insertBefore(foto, divFoto.firstChild);
				
				document.getElementById('btnAlterarAvatar').removeAttribute('hidden');
				document.getElementById('avatar').setAttribute('hidden', "true");
				
				document.getElementById('avatar').files[0] = {name: contato.fotoNome};
						
				document.getElementById('selectGrupos').setAttribute('value', contato.idGrupo);						
				var optionsGrupo = document.getElementsByClassName('optionGrupos');
				for (let item of optionsGrupo) {
					let certo =  item.value;
					if(contato.idGrupo == item.value) {
						item.setAttribute('selected', 'true');
					}
				}
			});			
	}
}

function editarArquivo(){	
	document.getElementById('avatar').removeAttribute('hidden');
	document.getElementById('btnAlterarAvatar').setAttribute('hidden', "true");
}

function editarContato(){
	event.preventDefault()
	const url = new URLSearchParams(window.location.search);
    const id = url.get("id");
	let nome = document.getElementById('nome').value;
    let nomeCompleto = document.getElementById('nomeCompleto').value;
    let email = document.getElementById('email').value;
	let telefone = document.getElementById('telefone').value;
	let idGrupo = document.getElementById('selectGrupos').value;
	let dataNascimento = document.getElementById('dataNascimento').value;
	let foto = document.getElementById('avatar').files[0];
	console.log(foto)

    const contatoAtt = {
        id: id,
        nome: nome,
        nomeCompleto: nomeCompleto,
		email: email,
		telefone: telefone,
		idGrupo: idGrupo,
		dataNascimento: dataNascimento,
		foto: null,
		fotoNome: foto ? foto.name : null,
        fotoTamanho: foto ? foto.size : null,
        fotoTipo: foto ? foto.type : null,
    }
	var validar = validacao(contatoAtt);
	
	let formData = new FormData();
	formData.append("contato", JSON.stringify(contatoAtt));
	formData.append("file", foto);
	
	if(validar) {
		fetch('http://localhost:8081/Basico/rs/contatos', {
	        method: 'PUT',			
	        body: formData
	    }).then(response => {
			alert("Contato atualizado com sucesso!");
	        window.location = '/Basico/index.html';
	    });
	}
}

function validacao(campos){
	var validar = false;
	console.log(campos)
	if (campos.nome == "" || campos.email == "" || campos.telefone == "" || 
	campos.fotoNome == null || campos.dataNascimento == "" || campos.idGrupo == "") {
		validar = false;		
		if (campos.nome  == ""){
			document.getElementById('errorNome').removeAttribute('hidden');	
		} else {
			document.getElementById('errorNome').setAttribute('hidden', "true");
		}		
		if (campos.email  == ""){
			document.getElementById('errorEmail').removeAttribute('hidden');	
		} else {
			document.getElementById('errorEmail').setAttribute('hidden', "true");
		}
		if (campos.telefone  == ""){
			document.getElementById('errorTelefone').removeAttribute('hidden');	
		} else {
			document.getElementById('errorTelefone').setAttribute('hidden', "true");
		}
		if (campos.dataNascimento  == ""){
			document.getElementById('errorDtNascimento').removeAttribute('hidden');	
		} else {
			document.getElementById('errorDtNascimento').setAttribute('hidden', "true");
		}
		if (campos.fotoNome  == null){
			document.getElementById('errorAvatar').removeAttribute('hidden');	
		} else {
			document.getElementById('errorAvatar').setAttribute('hidden', "true");
		}
		if (campos.idGrupo  == ""){
			document.getElementById('errorGrupo').removeAttribute('hidden');	
		} else {
			document.getElementById('errorGrupo').setAttribute('hidden', "true");
		}
	} else {
		validar = true;
	}	
	return validar;
}

function validaCadastroGrupo(campo){
	var validar = false;
	
	if (campo.nome  == ""){
		document.getElementById('errorNomeGrupo').removeAttribute('hidden');	
		validar = false;
	} else {
		document.getElementById('errorNomeGrupo').setAttribute('hidden', "true");
		validar = true;
	}
	return validar;
}

function listarGrupos(){
	fetch('http://localhost:8081/Basico/rs/grupos')
	.then(response => response.json())
        .then(dados => {
            dados.forEach(grupo => {
				let nome = grupo.nome;
				let id = grupo.id;
							
                let lista = document.getElementById('tabelaGrupos');
				var tr = document.createElement('tr');				
    			var tdCol1 = tr.appendChild(document.createElement('td'));
				tdCol1.innerHTML = id;				
				var tdCol2 = tr.appendChild(document.createElement('td'));
				tdCol2.innerHTML = nome;
								
				var tdCol4 = document.createElement('td');							
				let btnEditar = document.createElement('button');
                tdCol4.appendChild(btnEditar);
                btnEditar.setAttribute('class', "btn btn-info btn-sm btnEditar");
				btnEditar.innerHTML = "Editar";
                btnEditar.setAttribute('onclick', "buscaGrupo(" + grupo.id + ")");
				let btnExcluir = document.createElement('button');
				tdCol4.appendChild(btnExcluir);
                btnExcluir.setAttribute('class', "btn btn-danger btn-sm");
				btnExcluir.innerHTML = "Excluir";
				btnExcluir.setAttribute('onclick', "deletarGrupo(" + grupo.id + ")");
				tr.appendChild(tdCol4);												
                lista.appendChild(tr);
			}); 
		})	
}

function novoGrupo() {
	event.preventDefault();
	let nome = document.getElementById('nomeGrupo').value;
    const grupoAdd = {
        id: null,
        nome: nome,
    }
	var validar = validaCadastroGrupo(grupoAdd);
	resposta = JSON.stringify(grupoAdd);
	if(validar){
		fetch('http://localhost:8081/Basico/rs/grupos', {
	        method: 'POST',
			headers: {
				"Content-type": "application/json"
			},
	        body: resposta
	    }).then(response => {
			alert("Grupo adicionado com sucesso!");
	        window.location = '/Basico/pages/grupos.html';
	    });
	}
}

function buscaGrupo(id){
	window.location = "/Basico/pages/cadastrarGrupos.html?id=" + id;
}

function verificaGrupo() {
	const url = new URLSearchParams(window.location.search);
    const id = url.get("id");
    if (id != null) {		
		fetch('http://localhost:8081/Basico/rs/grupos/' + id)
		.then(response => response.json())
    	    .then(grupo => {	
				document.getElementById('btnSalvar').setAttribute('hidden', "true");
				document.getElementById('btnEditar').removeAttribute('hidden');	
				document.getElementById('nomeGrupo').setAttribute('value', grupo.nome);
				document.getElementById('idGrupo').setAttribute('value', grupo.id);				
			});			
	}
}

function editarGrupo(){
	const url = new URLSearchParams(window.location.search);
    const id = url.get("id");
	let nome = document.getElementById('nomeGrupo').value;

    const grupoAtt = {
        id: id,
        nome: nome,
    }
	resposta = JSON.stringify(grupoAtt);
	var validar = validaCadastroGrupo(grupoAtt);
	if(validar) {
		fetch('http://localhost:8081/Basico/rs/grupos', {
	        method: 'PUT',
			headers: {
				"Content-type": "application/json"
			},
	        body: resposta
	    }).then(response => {
			alert("Grupo atualizado com sucesso!");
	       window.location = '/Basico/pages/grupos.html';
	    }).catch(e => {
			alert("Erro ao atualizar grupo.");
	});
	}

}

function deletarGrupo(id){
	var confirmar = confirm("Confirmar exclusão do grupo")
	if (confirmar) {		
		let modalMensagem = true;
		fetch('http://localhost:8081/Basico/rs/grupos/' + id, {
		method: 'DELETE'
	}).then(response => {
			alert("Grupo excluído com sucesso!");
	        window.location = '/Basico/pages/grupos.html';			
	    });;
	}	
}
