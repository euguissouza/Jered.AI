const CHAVE = "jared-a11y";
const FONTE_MIN = 85;
const FONTE_MAX = 150;
const PASSO = 10;

const estado = { fonte: 100, contraste: false, ...lerEstado() };

function lerEstado() {
  try {
    return JSON.parse(localStorage.getItem(CHAVE)) ?? {};
  } catch {
    return {};
  }
}

function aplicar() {
  document.documentElement.style.fontSize = estado.fonte + "%";
  document.body.classList.toggle("alto-contraste", estado.contraste);
  document
    .querySelector("[data-a11y='contraste']")
    ?.setAttribute("aria-pressed", String(estado.contraste));
  try {
    localStorage.setItem(CHAVE, JSON.stringify(estado));
  } catch {
    
  }
}

function aoClicar(acao, funcao) {
  document.querySelector(`[data-a11y='${acao}']`)?.addEventListener("click", () => {
    funcao();
    aplicar();
  });
}

aoClicar("menos", () => { estado.fonte = Math.max(FONTE_MIN, estado.fonte - PASSO); });
aoClicar("mais", () => { estado.fonte = Math.min(FONTE_MAX, estado.fonte + PASSO); });
aoClicar("contraste", () => { estado.contraste = !estado.contraste; });

aplicar();
