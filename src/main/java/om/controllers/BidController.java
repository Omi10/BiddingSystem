package om.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import inti.ws.spring.exception.client.BadRequestException;
import om.entities.User;
import om.models.BidModel;
import om.models.MakeBidModel;
import om.models.ResultModel;
import om.services.BidService;

@RestController
public class BidController {

	@Autowired
	BidService bidService;

	@RequestMapping(value = "/items/{itemId}/bids", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)

	public List<BidModel> getAllBids(@PathVariable int itemId) throws BadRequestException {
		List<BidModel> bidModels = null;
		bidModels = bidService.getBids(itemId);
		return bidModels;
	}

	@RequestMapping(value = "user/{userId}/bids", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<BidModel> getUserBids(@PathVariable int userId, HttpSession session) throws BadRequestException {
		List<BidModel> bidModels = null;
		User user = (User) session.getAttribute("user");
		userId = user.getId();
		bidModels = bidService.getUserBids(userId);
		return bidModels;
	}

	@RequestMapping(value = "items/{itemId}/bids", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public MakeBidModel makeBid(@PathVariable int itemId, @RequestBody MakeBidModel makeBidModel, HttpSession session)
			throws BadRequestException {
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		makeBidModel.setBidderId(userId);
		return bidService.addBid(itemId, makeBidModel);
	}

	@RequestMapping(value = "items/{itemId}/bids/result", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResultModel bidResult(@PathVariable int itemId) throws BadRequestException {
		ResultModel resultModel = bidService.getBidResult(itemId);
		return resultModel;
	}

	@RequestMapping(value = "/bids/{bidId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteBid(@PathVariable int bidId) {
		bidService.deleteBid(bidId);
	}

	@RequestMapping(value = "/bids/{bidId}", method = RequestMethod.PATCH)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public BidModel updateBid(@PathVariable int bidId, @RequestBody BidModel bidModel) throws BadRequestException {
		return bidService.updateBid(bidId, bidModel);
	}

	@RequestMapping(value = "/bids/{bidId}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public BidModel getBid(@PathVariable int bidId) {
		return bidService.getBid(bidId);
	}

}
