package demo.rest;

import demo.domain.Location;
import demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningBulkUploadController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Location> locations) {
        locationService.saveRunningLocations(locations);

    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        locationService.deleteAll();
    }

    @RequestMapping(value = "/running/{movementType}", method = RequestMethod.GET)
    public Page<Location> findByLocationType(@PathVariable String movementType,
                                             @RequestParam(name = "page",required = false) Integer page,
                                             @RequestParam(name = "size",required = false) Integer size) {
        return this.locationService.findByRunnerMovementType(movementType, new PageRequest(page, size));
    }
}
