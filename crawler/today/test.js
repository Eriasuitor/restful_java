// const request = require('request')

// request.get({
//     url: 'http://soogif.com/showimg?labelId=13&start=333&size=20',
//     headers: {
//         'Accept': 'application/json'
//     }
// }, (e, r, b) => {
//     console.log(b)
// })

var rp = require('request-promise')


rp.get('http://soogif.com/showimg?labelId=13&start=333&size=20').then(body => {
    console.log(body)
})
