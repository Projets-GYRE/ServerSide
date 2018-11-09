package ch.kalajdzic.web.service;

import ch.kalajdzic.sudoku.grid.detection.SolvedPhoto;
import ch.kalajdzic.web.model.SudokuResolverService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class ApiController {


    private SudokuResolverService sudokuResolverService = new SudokuResolverService();

    @ResponseBody
    @RequestMapping(value = "/default", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public WholeData getWholeData(@RequestBody CameraPictureData cameraPictureData) {
        final SolvedPhoto solvedPhoto = sudokuResolverService.resolve(cameraPictureData.getPhotoBase64());
        return new WholeData(solvedPhoto);
    }
}
