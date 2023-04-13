const express = require('express')
const User = require('../models/user')

const app = express()

app.post('/register', async (req, res, next) => {
    // ruleid: express-mongodb-nosqli
    let user = new User(req.body)
    await user.save()
    return res.json(user)
})

app.post('/login', async (req, res, next) => {
    let { username, password } = req.body
    // ruleid: express-mongodb-nosqli
    let user = User.findOne({ username, password })
    return res.json(user)
})

app.post('/secure-login', async (req, res, next) => {
    let { username, password } = req.body
    // ok: express-mongodb-nosqli
    let user = User.findOne({ username: username.toString(), password: password.toString() })
    return res.json(user)
})
