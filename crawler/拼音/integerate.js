const fs = require('fs')

let py = fs.readFileSync('index.txt').toString()
let tz = fs.readFileSync('tiaozhan2.txt').toString()

let map = new Map()
let s = [py, tz]
let repeat = 0
    
    py.split(',,').map(_2 => {
        let json = JSON.parse(_2)
        json.id = Number(json.id)
        if (map.has(json.titleList[0] + json.titleList[1])) {
            console.log(`Repeat ${++repeat}:`, map.get(json.titleList[0] + json.titleList[1]), json)
        }
        else
            map.set(json.titleList[0] + json.titleList[1], json)
    })
    tz.split('\n,').map(_2 => {
        let json = JSON.parse(_2)
        json.id = Number(json.id)
        if (map.has(json.titleList[0] + json.titleList[1])) {
            console.log(`Repeat ${++repeat}:`, map.get(json.titleList[0] + json.titleList[1]), json)
        }
        else
            map.set(json.titleList[0] + json.titleList[1], json)
    })

let data = []
map.forEach(_ => data.push(_))
console.log('now: ' + data.length)
fs.writeFileSync('integer.json', data.map(_ => JSON.stringify(_) + '\n'))