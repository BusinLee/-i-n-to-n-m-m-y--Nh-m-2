//
//  ViewController.swift
//  StarKids1
//
//  Created by Thanh Lê on 3/24/19.
//  Copyright © 2019 Thanh Le. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var btnFirst: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        btnFirst.layer.cornerRadius = 0.5 * btnFirst.bounds.size.width;
    }


}

