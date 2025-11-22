function confirmar(idcon){
    let resposta = confirm("Confirma a exclusão desse contato?")
    if(resposta){
        window.location.href = "deletar?idcon="+idcon
    }
}