function validar(){
    let nome = update.nome.value
    let fone = update.fone.value

    if(nome === ""){
        alert("Preencha o campo nome.")
        update.nome.focus()
        return false
    }
    else if (fone === ""){
        alert("Preencha o campo telefone.")
        update.fone.focus()
        return false
    }
    else {
        document.forms["update"].submit()
    }
}