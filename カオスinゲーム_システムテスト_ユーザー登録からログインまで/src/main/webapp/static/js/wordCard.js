"use strict";

var words = [
  { key: "三角州", a: "河口付近において、河川によって運ばれた物質が堆積することにより形成された地形" },
  { key: "扇状地", a: "狭い山間地を流れる急流河川が広い平坦地に出た時、その流れが弱まることにより、運ばれてきた土砂が扇状に堆積してできた土地" },
  { key: "軟弱地盤の例", a: "湿地、後背湿地、河原、三角州、谷底平野" },
  { key: "他の土地に比べて標高の（　）い場所は、地盤が弱くなる。", a: "低い" }
];
var stock = [];

let card = document.getElementById("card");
let cardFront = document.getElementById("card-front");
let cardBack = document.getElementById("card-back");
let btn = document.getElementById("btn");
card.addEventListener("click", function() {
  flip();
});

btn.addEventListener("click", function() {
  next();
});

function flip() {
  card.className = card.className === "" ? "open" : "";
}

function next() {
  if (card.className === "open") {
    card.addEventListener("transitionend", setCard);
    flip();
  } else {
    setCard();
  }
}

function setCard() {
  var num = Math.floor(Math.random() * words.length);
  cardFront.innerHTML = words[num]["key"];
  cardBack.innerHTML = words[num]["a"];
  card.removeEventListener("transitioned", setCard);
}
