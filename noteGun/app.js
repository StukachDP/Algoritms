//В пустой клетке, рядом с которой ровно три живых соседа, появляется жизнь.
//Если в заполненной клетке меньше двух соседей или больше трех, то она умирает, иначе живет
//

let canvas = document.getElementById("canvas");
let ctx = canvas.getContext("2d");
const indexW = 40;
const indexH = 40;
let noteArray = [];
let timer;

canvas.onclick = function(event) {

    let x = event.offsetX;
    let y = event.offsetY;
    console.log(x);
    console.log(y);
    x = Math.floor(x / 10);
    y = Math.floor(y / 10);
    noteArray[x][y] = 1;
    console.log(noteArray);
    drawField();
}

function goToLife() {

    for (let i = 0; i < indexW; i++) {
        noteArray[i] = [];
        for (let j = 0; j < indexH; j++) {
            noteArray[i][j] = 0;
        }
    }
}
goToLife();


function drawField() {
    ctx.clearRect(0, 0, 400, 400);
    for (var i = 0; i < indexW; i++) {
        for (var j = 0; j < indexH; j++) {
            if (noteArray[i][j] == 1) {
                ctx.fillStyle = "green";
                ctx.fillRect(i * 10, j * 10, 10, 10);
            } else {
                ctx.fillStyle = "beige";
                ctx.fillRect(i * 10, j * 10, 9, 9);
            }
        }

    }
}

function makingDotLifestyle() {

    var noteArrayCreating = [];
    for (let i = 0; i < indexW; i++) {

        noteArrayCreating[i] = [];
        for (let j = 0; j < indexH; j++) {

            let neighborsCount = neighborsCounting(i, j);
            if (neighborsCount == 3) {
                noteArrayCreating[i][j] = 1;
            }
            if (neighborsCount == 2) {
                noteArrayCreating[i][j] = noteArray[i][j];
            }
            if (neighborsCount < 2 || neighborsCount > 3) {
                noteArrayCreating[i][j] = 0;
            }
        }
    }
    noteArray = noteArrayCreating;
    drawField();

}

function lifeInfinity() {
    makingDotLifestyle();
    timer = setTimeout(lifeInfinity, 300);
}


function lifeInputIterations() {
    let countOfIteration = document.getElementById("number-iteration").value;
    for (let k = 0; k < countOfIteration; k++) {
        makingDotLifestyle();
    }
}

function makingInfinityFieldPlus(coordinate) {
    if (coordinate === 39) {
        return -1;
    } else {
        return coordinate;
    }
}

function makingInfinityFieldMinus(coordinate) {
    if (coordinate === 0) {
        return 40;
    } else {
        return coordinate;
    }
}


function neighborsCounting(lineOfNote, columnOfNote) {

    let count = 0;

    if (noteArray[lineOfNote][makingInfinityFieldMinus(columnOfNote) - 1] == 1) {
        count++;
    } //left
    if (noteArray[lineOfNote][makingInfinityFieldPlus(columnOfNote) + 1] == 1) {
        count++;
    } //right
    if (noteArray[makingInfinityFieldPlus(lineOfNote) + 1][columnOfNote] == 1) {
        count++;
    } //bottom
    if (noteArray[makingInfinityFieldMinus(lineOfNote) - 1][columnOfNote] == 1) {
        count++;
    } //top

    if (noteArray[makingInfinityFieldMinus(lineOfNote) - 1][makingInfinityFieldMinus(columnOfNote) - 1] == 1) {
        count++;
    } //top-left
    if (noteArray[makingInfinityFieldMinus(lineOfNote) - 1][makingInfinityFieldPlus(columnOfNote) + 1] == 1) {
        count++;
    } //top-right
    if (noteArray[makingInfinityFieldPlus(lineOfNote) + 1][makingInfinityFieldMinus(columnOfNote) - 1] == 1) {
        count++;
    } //bottom-left
    if (noteArray[makingInfinityFieldPlus(lineOfNote) + 1][makingInfinityFieldPlus(columnOfNote) + 1] == 1) {
        count++;
    } //bottom-right

    return count;
}

document.getElementById("start-input-button").onclick = lifeInputIterations;
document.getElementById("start-infinity-button").onclick = lifeInfinity;