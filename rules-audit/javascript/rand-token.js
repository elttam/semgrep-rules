// Create a token generator with the default settings:
var randtoken = require('rand-token');
// Generate a 16 character alpha-numeric token:
// ruleid: rand-token-insecure-rng
var token = randtoken.generate(16);

const randtoken = require('rand-token');
// ruleid: rand-token-insecure-rng
var token = randtoken.generate(16);

let randtoken = require('rand-token');
// ruleid: rand-token-insecure-rng
var token = randtoken.generate(16);

import * as randtoken from 'rand-token';
// ruleid: rand-token-insecure-rng
var token = randtoken.generate(16);

// Use it as a replacement for uid:
// ruleid: rand-token-insecure-rng
var uid = require('rand-token').uid;
var token = uid(16);

// Generate mostly sequential tokens:
// ruleid: rand-token-insecure-rng
var suid = require('rand-token').suid;
var token = suid(16);



// Create a token generator with the default settings:
// ruleid: rand-token-insecure-rng
var uid = require('rand-token').uid;

// Generate a 16 character alpha-numeric token:
var token = uid(16)



// Create a token generator with the default settings:
// ruleid: rand-token-insecure-rng
var suid = require('rand-token').suid;

// Generate a 24 (16 + 8) character alpha-numeric token:
var token = suid(16)



// Create a token generator with the default settings:
var generatorC = require('rand-token').generator({
  chars: 'a-z'
});

// Generate a 16 character token:
// ruleid: rand-token-insecure-rng
var token = generatorC.generate(16);



// Create a token generator with the default settings:
var randtoken = require('rand-token').generator();

// Generate a 16 character token:
// ruleid: rand-token-insecure-rng
var token = randtoken.generate(16, "abcdefghijklnmopqrstuvwxyz");


var crypto = require('crypto');
// Create the generator:
var generator = require('rand-token').generator({
  chars: 'A-Z',
  source: crypto.pseudoRandomBytes
});

// Generate a 16 character token:
// ruleid: rand-token-insecure-rng
var token = generator.generate(16);


var randtoken = require('rand-token');
// `crypto.randomBytes` generators cryptographically strong pseudorandom data.
var generatorPRB = randtoken.generator({source: crypto.pseudoRandomBytes});
// Generate a 16 character token:
// ruleid: rand-token-insecure-rng
var token = generatorPRB.generate(16);


import * as randtokenI from 'rand-token';
var generatorPRB2 = randtokenI.generator({source: crypto.pseudoRandomBytes});
// ruleid: rand-token-insecure-rng
var token = generatorPRB2.generate(16);



//////// Cryptographically Secure Uses - These should be OK and not flagged ////////////////

var crypto = require('crypto');
// Create the generator:
var generatorRB = require('rand-token').generator({
  chars: 'A-Z',
  source: crypto.randomBytes
});

// Generate a 16 character token:
// ok: rand-token-insecure-rng
var token1 = generatorRB.generate(16);
// ok: rand-token-insecure-rng
var token2 = generatorRB.generate(16, "abcdefghijklnmopqrstuvwxyz");


var randtoken = require('rand-token');
// `crypto.randomBytes` generators cryptographically strong pseudorandom data.
var generatorRB2 = randtoken.generator({source: crypto.randomBytes});
// Generate a 16 character token:
// ok: rand-token-insecure-rng
var token3 = generatorRB2.generate(16);
// ok: rand-token-insecure-rng
var token4 = generatorRB2.generate(16, "abcdefghijklnmopqrstuvwxyz");
