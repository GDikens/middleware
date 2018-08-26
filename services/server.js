var express = require('express');
const router = express.Router();

var app = express();

const hostname = '127.0.0.1';
const port = 3000;

/* const server = http.createServer((req, res) => {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/plain');            
});

server.listen(port, hostname, (request, response) => {
    console.log(`Server running at http://${hostname}:${port}/`);
    if ((hostname=='localhost')&&(port=="9999")){
        //wtf
    }    
}); 
*/

app.listen(port, hostname, function(){
    console.log(`Express server running at http://${hostname}:${port}/`);
});


app.use('/gcd',(req, res, next) => {

    console.log("gcd function called!");

    var a = Math.abs(req.query.num1);
    var b = Math.abs(req.query.num2);
    
    if (b > a) {var temp = a; a = b; b = temp;}
    
    while (true) {
        if (b == 0){
            res.json(a);
        }else{
            a %= b;
        }
        if (a == 0){
            res.json(b);
        }
        else{
            b %= a;
        }
    }

});

router.get('/fibbonachi', (req, res, next) => {

    var a = 1, b = 0, temp;
  
    while (num >= 0){
      temp = a;
      a = a + b;
      b = temp;
      num--;
    }
  
    res,json(b);

});

// function gcd 
function findGCD(a,b) {
    a = Math.abs(a);
    b = Math.abs(b);
    if (b > a) {var temp = a; a = b; b = temp;}
    while (true) {
        if (b == 0){
             return a;
        }else{
            a %= b;
        }
        if (a == 0){
            return b;
        }
        else{
            b %= a;
        }
    }
}

// function fibbonachi
function fibonacci(num){
    var a = 1, b = 0, temp;
  
    while (num >= 0){
      temp = a;
      a = a + b;
      b = temp;
      num--;
    }
  
    return b;
}
