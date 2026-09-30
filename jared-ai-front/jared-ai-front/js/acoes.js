export function iniciarAcoes(elementoTexto, avisar) {
  const btnCopiar = document.getElementById("btn-copiar");
  const btnOuvir = document.getElementById("btn-ouvir");
  const btnParar = document.getElementById("btn-parar");

  btnCopiar.addEventListener("click", async () => {
    try {
      await navigator.clipboard.writeText(elementoTexto.innerText);
      avisar("Resumo copiado.", "ok");
    } catch {
      avisar("Não foi possível copiar automaticamente. Selecione o texto e use Ctrl+C.", "erro");
    }
  });

  if (!("speechSynthesis" in window)) {
    btnOuvir.hidden = true;
    btnParar.hidden = true;
    return;
  }

  btnOuvir.addEventListener("click", () => {
    speechSynthesis.cancel();
    const fala = new SpeechSynthesisUtterance(elementoTexto.innerText);
    fala.lang = "pt-BR";
    speechSynthesis.speak(fala);
  });

  btnParar.addEventListener("click", () => speechSynthesis.cancel());
}
