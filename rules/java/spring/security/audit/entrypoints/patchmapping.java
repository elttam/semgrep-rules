package entrypoints;

class Example {
    // ruleid: rest-PatchMapping
    @PatchMapping("/heavyresource/{id}")
    public ResponseEntity<?> partialUpdateName(@RequestBody HeavyResourceAddressOnly partialUpdate,
            @PathVariable("id") String id) {

        heavyResourceRepository.save(partialUpdate, id);
        return ResponseEntity.ok("resource address updated");
    }
}
