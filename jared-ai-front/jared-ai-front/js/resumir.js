import { CONFIG } from "./config.js";
import { resumirArquivo } from "./api.js";
import { iniciarAcoes } from "./acoes.js";

const $ = (id) => document.getElementById(id);
const zona = $("zona-upload");
const entrada = $("arquivo");
const nomeArquivo = $("nome-arquivo");
const botao = $("btn-resumir");
const status = $("status");
const resultado = $("resultado");
const texto = $("texto-resumo");

let arquivoAtual = null;

function mostrarStatus(mensagem, tipo = "info") {
  status.textContent = mensagem;
  status.dataset.tipo = tipo;
  status.hidden = !mensagem;
}

function validar(arquivo) {
  const extensao = arquivo.name.split(".").pop().toLowerCase();
  if (!CONFIG.EXTENSOES.includes(extensao)) {
    return `Formato .${extensao} não é aceito. Use: ${CONFIG.EXTENSOES.join(", ")}.`;
  }
  if (arquivo.size > CONFIG.TAMANHO_MAX_MB * 1024 * 1024) {
    return `O arquivo passa de ${CONFIG.TAMANHO_MAX_MB} MB.`;
  }
  return null;
}

function escolherArquivo(arquivo) {
  if (!arquivo) return;

  const erro = validar(arquivo);
  if (erro) {
    arquivoAtual = null;
    botao.disabled = true;
    nomeArquivo.textContent = "";
    mostrarStatus(erro, "erro");
    return;
  }

  arquivoAtual = arquivo;
  nomeArquivo.textContent = arquivo.name;
  botao.disabled = false;
  mostrarStatus("");
}

function mostrarResumo(resumo) {
  
  const paragrafos = resumo.split(/\n{2,}/).map((conteudo) =>
    Object.assign(document.createElement("p"), { textContent: conteudo })
  );
  texto.replaceChildren(...paragrafos);
  resultado.hidden = false;
  resultado.focus();
}

async function gerarResumo() {
  if (!arquivoAtual) return;

  botao.disabled = true;
  resultado.hidden = true;
  mostrarStatus("Lendo o arquivo e preparando o resumo…");

  try {
    const resumo = await resumirArquivo(arquivoAtual);
    if (!resumo) throw new Error("Nenhum resumo foi devolvido.");
    mostrarStatus("");
    mostrarResumo(resumo);
  } catch (erro) {
    mostrarStatus(erro.message, "erro");
  } finally {
    botao.disabled = false;
  }
}


entrada.addEventListener("change", () => escolherArquivo(entrada.files[0]));


["dragenter", "dragover"].forEach((evento) =>
  zona.addEventListener(evento, (e) => {
    e.preventDefault();
    zona.classList.add("ativa");
  })
);
["dragleave", "drop"].forEach((evento) =>
  zona.addEventListener(evento, (e) => {
    e.preventDefault();
    zona.classList.remove("ativa");
  })
);
zona.addEventListener("drop", (e) => escolherArquivo(e.dataTransfer.files[0]));


["dragover", "drop"].forEach((evento) =>
  window.addEventListener(evento, (e) => e.preventDefault())
);

botao.addEventListener("click", gerarResumo);
iniciarAcoes(texto, mostrarStatus);
