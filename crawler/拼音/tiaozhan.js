const request = require('request')
const fs = require('fs')

let result = []
let map = new Map()
let finished = false
let id = 1001

function finish() {
    map.forEach(_ => result.push(_))
    fs.writeFileSync('tiaozhan2.json', result.map(_ => JSON.stringify(_) + '\n'))
}

let action = new Promise(async resolve => {
    let j = 1
    while (j < 6) {
        console.log('start to run level ' + j)
        let i = 0
        while (i < 360) {
            await new Promise(resolve => {
                request({
                    url: `https://www.ruiwen.com/app_phpv2/pinyin/index.php?app=pytz&lv=${j}`,
                    method: 'get',
                    headers: {
                        cookie: 'PHPSESSID=YnCt3ApBdf9038d18290059d53da22f8940b121e'
                    }
                    // "id": "919",
                    // "titleList": ["邛", "崃"],
                    // "answerList": ["qióng", "qiū"],
                    // "signIndex": 0,
                    // "correctIndex": 0
                }, (error, response, body) => {
                    if (error) reject(error);
                    let data = JSON.parse(body)
                    for (let i = 0; i < data.questions.length; i++) {
                        if (!map.has(data.questions[i][0] + data.questions[i][1]))
                            map.set(data.questions[i][0] + data.questions[i][1], {
                                id: (id++).toString(),
                                titleList: data.questions[i],
                                answerList: data.selects[i],
                                signIndex: data.questions[i].indexOf(data.words[i]),
                                correctIndex: data.answers[i]
                            })
                    }
                    resolve()
                })
            }).catch(error => {
                console.log(error)
            })
            console.log(map.size)
            if (finished) {
                console.log('time out')
                break
            }
            i++
            await new Promise(resolve => {
                setTimeout(resolve, 1000)
            })
        }
        j++
    }
    resolve('finished')
})


let counter = new Promise((resolve, reject) => {
    setTimeout(resolve, 3500000, 'time out')
})

Promise.race([action, counter]).then(value => {
    console.log(value)
    finished = true
    finish()
}).catch(error => {
    console.log(error)
    finished = true
    finish()
})

    // let i = 1
    // while (i <= 17) {
    //     let writeStream = fs.createWriteStream(`level/nor_${i}.png`)
    //     let writeStream2 = fs.createWriteStream(`level/sel_${i}.png`)
    //     await new Promise(resolve => {
    //         writeStream.on('finish', () => {
    //             console.log(2)

    //             writeStream2.on('finish', () => {
    //                 console.log(1)
    //                 i++
    //                 resolve()
    //             })
    //             request.get({ url: `http://xcxcy.oss-cn-hangzhou.aliyuncs.com/cycck/level/sel_${i}.png` }).pipe(writeStream2)
    //         })
    //         request.get({ url: `http://xcxcy.oss-cn-hangzhou.aliyuncs.com/cycck/level/nor_${i}.png` }).pipe(writeStream)
    //     })
    // }




// var qs = require("querystring");
// var http = require("https");

// var options = {
//     "method": "POST",
//     "hostname": 'pinyin.zaml2018.com',
//     "path": "/pyjyz/api/v1_0_5/challenge/start",
//     "headers": {
//         "Content-Type": "application/x-www-form-urlencoded",
//         "Cache-Control": "no-cache",
//     }
// };

// var req = http.request(options, function (res) {
//     var chunks = [];

//     res.on("data", function (chunk) {
//         chunks.push(chunk);
//     });

//     res.on("end", function () {
//         var body = Buffer.concat(chunks);
//         console.log(body.toString());
//     });
// });

// req.write(qs.stringify({
//     user_id: '164189',
//     timestamp: '1534830186685',
//     sign: '501a10bbed32ccc9e1a4eb8f6c9ca815'
// }));
// console.log(qs.stringify({
//     user_id: '164189',
//     timestamp: '1534830186685',
//     sign: '501a10bbed32ccc9e1a4eb8f6c9ca815'
// }))
// req.end();