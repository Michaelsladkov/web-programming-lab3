let buffer = [];
let canv;
const rProportionY = 0.3375;
const rProportionX = 0.33;
const xOffset = -15;
const yOffset = -16;
const centerX = 145;
const centerY = 143;
const rPixels = 106;

function addShot(x, y, r, success) {
   let toPush = {
        "x": x,
        "y": y,
        "success": success,
        "r": r
   }
   buffer.push(toPush);
}

function init() {
    clearBuffer();
    canv = document.getElementsByTagName("canvas")[0];
    let img = document.getElementsByTagName("img")[0];
    canv.drawImage(img);
    document.getElementsByTagName("canvas")[0].onclick = processClick;
}

function clearBuffer() {
    buffer = [];
}

function getBuffer() {
    return buffer;
}

function drawDots() {
    console.log("called")
    canv.width=320
    canv.height=320
    canv.getContext("2d").clearRect(0, 0, canv.width, canv.height);
    for (let dot of buffer) {
        console.log("draw " + dot)
        drawDot(dot, canv);
    }
}

function drawDot({x, y, r, success}, canvas) {
    let context = canvas.getContext("2d");
    let drawnX = (rProportionY * (x / r) + 0.5 )* canvas.width + xOffset;
    let drawnY = (-rProportionX * (y / r) + 0.5 )* canvas.height + yOffset;

    console.log(success)
    if (success) {
        context.fillStyle = 'red';
        console.log("red");
    }
    else {
        context.fillStyle = 'blue';
        console.log("blue");
    }
    context.lineWidth = 1.5;
    context.strokeStyle = 'black';

    context.beginPath();
    context.arc(drawnX, drawnY, 3.5, 0, Math.PI * 2, false);
    context.stroke();
    context.closePath();
    context.fill();
}

function processClick(eventObj) {
    let clickX = eventObj.pageX - this.offsetLeft;
    let clickY = eventObj.pageY - this.offsetTop;
    let xToR = (clickX - centerX) / rPixels;
    let yToR = (-clickY + centerY) / rPixels;
    xToR = xToR.toFixed(2);
    yToR = yToR.toFixed(2);
    console.log(xToR);
    console.log(yToR);
    document.getElementById("j_idt14:hidden_x").value = xToR;
    document.getElementById("j_idt14:hidden_y").value = yToR;
    document.getElementsByClassName("fire-button")[0].click();
    document.getElementById("j_idt14:hidden_x").value = null;
    document.getElementById("j_idt14:hidden_y").value = null;
}
