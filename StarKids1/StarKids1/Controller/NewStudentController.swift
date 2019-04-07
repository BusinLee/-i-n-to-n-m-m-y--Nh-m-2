//
//  NewStudentController.swift
//  StarKids1
//
//  Created by Thanh Lê on 4/7/19.
//  Copyright © 2019 Thanh Le. All rights reserved.
//

import UIKit

class NewStudentController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var pickerBirthDay: UIPickerView!
    
    var arrDate = [[Int]]()
    //var arrDate:Array<Array<Int>> = [[],[]]
    
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
        
        let tap = UITapGestureRecognizer(target: self.view, action: Selector("endEditing:"))
        tap.cancelsTouchesInView = false
        self.view.addGestureRecognizer(tap)
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

}
