package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;
import org.superbiz.moviefun.albums.AlbumInfo;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private String albumsUrl;
    private RestOperations restOperations;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumsListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl + "/api";
        this.restOperations = restOperations;
    }

    @Transactional
    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl, album, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        ResponseEntity<AlbumInfo> responseEntity = restOperations.getForEntity(albumsUrl +"/"+id, AlbumInfo.class);
        return responseEntity.getBody();
    }

    public List<AlbumInfo> getAlbums() {
        ResponseEntity<List<AlbumInfo>> responseEntity = restOperations.exchange(albumsUrl, GET, null, albumsListType);
        return responseEntity.getBody();
    }

    @Transactional
    public void deleteAlbum(AlbumInfo album) {
        restOperations.delete(albumsUrl, album);
    }

    @Transactional
    public void updateAlbum(AlbumInfo album) {
        restOperations.put(albumsUrl, album);
    }
}
