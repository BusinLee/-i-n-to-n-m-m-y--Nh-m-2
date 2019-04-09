//
//  NewStudentController.swift
//  StarKids1
//
//  Created by Thanh Lê on 4/7/19.
//  Copyright © 2019 Thanh Le. All rights reserved.
//

import UIKit

class NewStudentController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var lbValid: UILabel!
    @IBOutlet weak var txtBirthYear: UITextField!
    @IBOutlet weak var txtFullName: UITextField!
    @IBOutlet weak var btnAddStudent: UIButton!
    @IBOutlet weak var imgAvatar: UIImageView!
    @IBOutlet weak var pickerBirthDay: UIPickerView!
    
    var arrDate = [[Int]]()
    var date:String = ""
    var day:String = "01"
    var month:String = "01"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pickerBirthDay.delegate = self
        pickerBirthDay.dataSource = self
        
        for i in 0...1 {
            var row = [Int]()
            for j in 0...30 {
                row.append(j+1)
                if (i==1 && j==11) {
                    break
                }
            }
            arrDate.append(row)
        }
        txtBirthYear.text = String(Calendar.current.component(.year, from: Date()) - 5)
        btnAddStudent.layer.cornerRadius = 5
        lbValid.isHidden = true
        
        let tap = UITapGestureRecognizer(target: self.view, action: Selector("endEditing:"))
        tap.cancelsTouchesInView = false
        self.view.addGestureRecognizer(tap)
    }
    
    @IBAction func tap_Avata(_ sender: UITapGestureRecognizer) {
        print("Dang tap")
        let alert:UIAlertController = UIAlertController(title: "Thông báo", message: "Chọn", preferredStyle: .alert)
        let btnPhoto:UIAlertAction = UIAlertAction(title: "Photo", style: .default) { (UIAlertAction) in
            let imgPicker = UIImagePickerController()
            imgPicker.sourceType = UIImagePickerController.SourceType.photoLibrary
            imgPicker.celegate = self
            imgPicker.allowsEditing = false
            self.present(imgPicker, animated: true, completion: nil)
        }
        let btnCamera:UIAlertAction = UIAlertAction(title: "Camera", style: .default) { (UIAlertAction) in
            
        }
        alert.addAction(btnPhoto)
        alert.addAction(btnCamera)
        
        self.present(alert, animated: true, completion: nil)
    }
    @IBAction func btn_AddStudent(_ sender: Any) {
        if ((month == "04" || month == "06" || month == "09" || month == "11") && day == "31") {
            lbValid.isHidden = false
        } else {
            if (month == "02" && (day == "30" || day == "31")) {
                lbValid.isHidden = false
            } else {
                if (month == "02" && day == "29" && (Int(txtBirthYear.text!)! % 4) != 0) {
                    lbValid.isHidden = false
                } else {
                    date = day + month + txtBirthYear.text!
                    lbValid.isHidden = true
                }
            }
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return arrDate.count
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return arrDate[component].count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return String(arrDate[component][row])
    }

    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        
        //var abc:String = String(arrDate[component][row])
        
        if (component == 0) {
            if (arrDate[component][row] < 10) {
                day = "0" + String(arrDate[component][row])
            } else {
                day = String(arrDate[component][row])
            }
        }
        else {
            if (arrDate[component][row] < 10) {
                month = "0" + String(arrDate[component][row])
            } else {
                month = String(arrDate[component][row])
            }
        }
    }
}
extension NewStudentController : UIImagePickerControllerDelegate, UINavigationControllerDelegate
{
    private func imagePickerController(_picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : AnyObject]) {
        let chooseImg = info[UIImagePickerControllerOriginalImage] as! UIImage
    }
}
