class Vehicle {
  // mutable
  private _year: number;
  private _licensePlate: string;

  // immutable
  private _model: string | undefined;
  private _producer: string | undefined;

  public constructor(
    year: number,
    licensePlate: string,
    model: string,
    producer: string
  ) {
    if (year >= 0) {
      this._year = year;
    } else {
      this._year = 0;
    }

    this._licensePlate = licensePlate;
    this._model = model;
    this._producer = producer;
  }

  public get year(): number {
    return this._year;
  }

  public set year(year: number) {
    if (year >= 0) this._year = year;
  }

  public get model(): string | undefined {
    return this._model;
  }

  public set model(model: string) {
    if (this._model === undefined) this._model = model;
  }

  public toString(): string {
    return this._producer + " " + this._model + " " + this._year;
  }

  public calcTax(tax: any): any {
    return tax <= 0 && tax < 1
      ? "tax must be between (0, 1]"
      : tax * this._year;
  }
}

const v = new Vehicle(-2002, "", "focus", "ford");
console.log(v.toString());
v.year = 2012;
console.log(v.toString());
v.model = "fiesta";
console.log(v.toString());
console.log(v.calcTax(-0.13));
