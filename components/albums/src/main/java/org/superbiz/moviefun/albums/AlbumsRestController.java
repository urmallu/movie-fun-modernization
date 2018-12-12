package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/api")
public class AlbumsRestController {

    private AlbumsRepository albumsRepository;

    public AlbumsRestController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album find(@PathVariable long id) {
        return albumsRepository.find(id);
    }

    @GetMapping
    public List<Album> getAlbums() {
        return albumsRepository.getAlbums();
    }

    @DeleteMapping
    public void deleteAlbum(@RequestParam Album album) {
        albumsRepository.deleteAlbum(album);
    }

    @PutMapping
    public void updateAlbum(@RequestParam Album album) {
        albumsRepository.updateAlbum(album);
    }


}
