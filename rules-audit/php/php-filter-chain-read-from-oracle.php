<?PHP
// ruleid: php-filter-chain-read-from-oracle
file_get_contents($_POST[0]);
// ok: php-filter-chain-read-from-oracle
file_get_contents($_POST[0] + "asdf");
// ok: php-filter-chain-read-from-oracle
file_get_contents("asdf" + $_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
readfile($_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
$file = new finfo(); $fileinfo = $file->file($_POST[0], FILEINFO_MIME);
// ruleid: php-filter-chain-read-from-oracle
getimagesize($_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
md5_file($_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
sha1_file($_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
hash_file('md5', $_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
file($_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
parse_ini_file($_POST[0]);
// ruleid: php-filter-chain-read-from-oracle
copy($_POST[0], '/tmp/test');
// ruleid: php-filter-chain-read-from-oracle
file_put_contents($_POST[0], "");
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_POST[0], "r"); stream_get_contents($file);
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_POST[0], "r"); fgets($file);
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_POST[0], "r"); fread($file, 10000);
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_POST[0], "r"); fgetc($file);
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_POST[0], "r"); fgetcsv($file, 1000, ",");
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_POST[0], "r"); fpassthru($file);
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_POST[0], "rw"); fputs($file, 0);
// ruleid: php-filter-chain-read-from-oracle
$file = fopen($_GET[0], "rw"); fputs($file, 0);

if (strlen($_POST['file']) < 1) {
    print('Test');
} elseif (!is_file($_POST['file'])) {
    print('It ain\'t a file');
} else {
    // ok: php-filter-chain-read-from-oracle
    $data = file_get_contents(urldecode($_POST['file']));
    if ($data === false) {
        print('Do something');
    } else {
        print('Do something else');
    }
}

// ok: php-filter-chain-read-from-oracle
$tasks = file_get_contents(INSTALL_ROOT.'resources/tasks.xml');

if (!empty($_FILES['meow']) && is_uploaded_file($_FILES['meow']['tmp_name'])) {
    // ok: php-filter-chain-read-from-oracle
    $pkcs12_file = file_get_contents($_FILES['meow']['tmp_name']);
}

$meow = $_GET['source'];
// todook: php-filter-chain-read-from-oracle
file_put_contents("{$blah1}/{$meow}", $test);

$id = $_REQUEST['test'];
$meow = g_get('tmp_path') . '/meow' . $id;
// todook: php-filter-chain-read-from-oracle
$old_targets = unserialize(file_get_contents($meow));
?>
