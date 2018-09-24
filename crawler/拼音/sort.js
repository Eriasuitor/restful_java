const fs = require('fs')

let data = fs.readFileSync('./integer.json').toString().split('\n,').map(_ => JSON.parse(_))
let degree1 = [], degree2 = []

for (let i = 1; i <= 1509; i++) {
    if (i >= 201 && i <= 318 || i >= 366 && i <= 400 || i >= 420 && i <= 435 || i >= 441 & i <= 505)
        degree2.push(data[i - 1])
    else {
        degree1.push(data[i - 1])
    }
}

fs.writeFileSync('sorted.txt', JSON.stringify({ degree1, degree2 }))