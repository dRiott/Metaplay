package metaplay.testControllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Date;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.thoughtriott.metaplay.controllers.BrowseController;
import com.thoughtriott.metaplay.data.entities.Album;
import com.thoughtriott.metaplay.data.entities.Artist;
import com.thoughtriott.metaplay.data.repositories.jpa.AlbumRepository;

public class BrowseControllerTest {

	@Test
	public void testViewBrowseAlbums() throws Exception {
		BrowseController browseController = new BrowseController();
		MockMvc mockMvc = standaloneSetup(browseController).build();
		mockMvc.perform(get("/browse/artists")).andExpect(view().name("browse_artists"));
	}

	@Test
	public void testViewSingleAlbum() throws Exception {
		Album expectedAlbum = new Album("Donuts", "J Dilla's last official release.", new Artist(), 7, new Date(), 70);
		AlbumRepository albumRepository = mock(AlbumRepository.class);
		when(albumRepository.getOne(12345)).thenReturn(expectedAlbum);

		BrowseController controller = new BrowseController(albumRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(get("/browse/album/12345")).andExpect(view().name("single_album"))
				.andExpect(model().attributeExists("album")).andExpect(model().attribute("album", expectedAlbum));
	}

}
