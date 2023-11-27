package com.sd64.novastore.controller.admin;

import com.sd64.novastore.model.Brand;
import com.sd64.novastore.model.Category;
import com.sd64.novastore.model.Form;
import com.sd64.novastore.model.Material;
import com.sd64.novastore.model.Product;
import com.sd64.novastore.model.Promotion;
import com.sd64.novastore.model.Voucher;
import com.sd64.novastore.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/nova/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/page")
    public String getAllPagination(@ModelAttribute("voucher") Voucher voucher, @RequestParam(name = "page", defaultValue = "0") Integer page,
                                   Model model) {
        Page<Voucher> pageVoucher = voucherService.getAll(page);
        model.addAttribute("pageVoucher", pageVoucher);
        model.addAttribute("page", page);
        return "admin/voucher/voucher";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        Voucher voucher = new Voucher();
        model.addAttribute("voucher", voucher);
        return "admin/voucher/voucher-add";
    }

    @PostMapping("/add")
    public String add( @ModelAttribute("voucher") Voucher voucher, RedirectAttributes redirectAttributes) {
        voucherService.add(voucher);
        redirectAttributes.addFlashAttribute("mess", "Thêm thành công!!");
        return "redirect:/nova/voucher/page";
    }

    @PostMapping("/update/{id}")
    public String update( @ModelAttribute("voucher") Voucher voucher, @PathVariable Integer id,RedirectAttributes redirectAttributes) {
        voucherService.update(voucher, id);
        redirectAttributes.addFlashAttribute("mess", "Sửa thành công!!");
        return "redirect:/nova/voucher/page";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        voucherService.delete(id);
        redirectAttributes.addFlashAttribute("mess", "Xoá thành công!!");
        return "redirect:/nova/voucher/page";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Voucher voucher = voucherService.getOne(id);
        model.addAttribute("voucher", voucher);
        return "admin/voucher/voucher-detail";
    }
    @GetMapping("/search")
    public String search(@ModelAttribute("voucher") Voucher voucher,Model model, @RequestParam(required = false) String voucherNameSearch,
                         @RequestParam(defaultValue = "0") int page) {
        Page<Voucher> pageVoucher = voucherService.search(voucherNameSearch, page);
        if ("".equals(voucherNameSearch) || voucherNameSearch.isEmpty()) {
            return "redirect:/nova/voucher/page";
        }
        model.addAttribute("voucherNameSearch", voucherNameSearch);
        model.addAttribute("pageVoucher", pageVoucher);
        return "admin/voucher/voucher";
    }
}
