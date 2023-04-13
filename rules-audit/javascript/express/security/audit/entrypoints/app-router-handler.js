const express = require('express');
const bodyParser = require('body-parser');

const app = express();
const router = express.Router();

// ok: express-app-router-handler
app.use(bodyParser());

// ruleid: express-app-router-handler
router.post('/admin/login', async (req, res) => {
    if(req.body.username == 'admin') {
        return res.redirect('/admin/dashboard');
    } else {
        return res.status(401).json({ error: 'Unauthorised' });
    }
});

// ruleid: express-app-router-handler
app.get('/admin/dashboard', async (req, res) => {
    return res.send('data');
});


let undef = undefined
// ok: express-app-router-handler
undef.get('/x', () => 1)
