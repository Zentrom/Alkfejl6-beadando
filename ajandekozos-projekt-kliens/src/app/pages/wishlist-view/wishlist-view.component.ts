import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../../app.component';
import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';

import { AuthService } from '../../services/auth.service';

/**
 * @title Basic CDK data-table
 */
@Component({
  selector: 'app-wishlist-view',
  templateUrl: './wishlist-view.component.html',
  styleUrls: ['./wishlist-view.component.css']
})
export class WishlistViewComponent implements OnInit {
  displayedColumns = ['userId', 'name', 'price', 'link'];
  exampleDatabase = new ExampleDatabase();
  dataSource: ExampleDataSource | null;

  authService: AuthService;

  constructor() { }

  ngOnInit() {
    this.dataSource = new ExampleDataSource(this.exampleDatabase);
    this.authService = AppComponent.authService;
  }
}

/** Constants used to fill up our data base. */
const COLORS = ['Maroon', 'Red', 'Orange', 'Yellow', 'Olive', 'Green', 'Purple',
  'Fuchsia', 'Lime', 'Teal', 'Aqua', 'Blue', 'Navy', 'Black', 'Gray'];
const NAMES = ['Car','Bike','WashingMachine','Carpet','Ball','Pencil',
  'Gun','Fork','Jacket', 'Knife', 'Juice', 'Monitor', 'Pork'];

export interface PresentData {
  id: string;
  name: string;
  price: number;
  link: string;
}

/** An example database that the data source uses to retrieve data for the table. */
export class ExampleDatabase {
  /** Stream that emits whenever the data has been modified. */
  dataChange: BehaviorSubject<PresentData[]> = new BehaviorSubject<PresentData[]>([]);
  get data(): PresentData[] { return this.dataChange.value; }

  constructor() {
    // Fill up the database with 100 users.
    for (let i = 0; i < 30; i++) { this.addPresent(); }


  }

  /** Adds a new user to the database. */
  addPresent() {
    const copiedData = this.data.slice();
    copiedData.push(this.createNewPresent());
    this.dataChange.next(copiedData);
  }


  private createNewPresent() {
    const name =
        COLORS[Math.round(Math.random() * (COLORS.length - 1))] + ' ' +
        NAMES[Math.round(Math.random() * (NAMES.length - 1))];

    return {
      id: (this.data.length + 1).toString(),
      name: name,
      price: (100 * Math.round(Math.random() * 100)),
      link: 'www.google.com'
    };
  }
}

/**
 * Data source to provide what data should be rendered in the table. Note that the data source
 * can retrieve its data in any way. In this case, the data source is provided a reference
 * to a common data base, ExampleDatabase. It is not the data source's responsibility to manage
 * the underlying data. Instead, it only needs to take the data and send the table exactly what
 * should be rendered.
 */
export class ExampleDataSource extends DataSource<any> {
  constructor(private _exampleDatabase: ExampleDatabase) {
    super();
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<PresentData[]> {
    return this._exampleDatabase.dataChange;
  }

  disconnect() {}
}
