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

class HalfFunctionalClass {
  private _list: any = [];

  constructor(list: any) {
    this._list = list;
  }

  public set list(list: any) {
    this._list = list;
  }

  public iter(): void {
    this._list.forEach((element: any) => console.log(element));
  }

  public map(fun: (arg: any) => any): void {
    this._list = this._list.map(fun);
  }

  public filter(pred: (arg1: any) => boolean): void {
    this._list = this._list.filter(pred);
  }

  public static currySum(arg1: any): any {
    return (arg2: any) => {
      if (typeof arg1 === "number" && typeof arg2 === "number") {
        return arg1 + arg2;
      } else {
        return arg1 > arg2 ? arg1 : arg2;
      }
    };
  }

  public reduce(fun: (arg1: any, arg2: any) => any, startVal: any): any {
    startVal = this._list.reduce(fun);
    return startVal;
  }
}

const f = new HalfFunctionalClass([1, 2, 3, 4]);
f.map((x) => x * x);
f.iter();
f.filter((x) => x % 2 == 0);
f.iter();
let func = HalfFunctionalClass.currySum(4);
let func1 = func(1235);
console.log(func1);
console.log("\n");
f.list = [1, 1, 1, 1];
f.iter();
console.log(f.reduce((x, y) => x + y, 0));

// Stwórz interfejs Shape, który będzie miał metodę calculateArea, zwracającą pole
// powierzchni figury. Następnie stwórz klasy Rectangle i Circle, które implementują
// ten interfejs i mają odpowiednie pola i metody do obliczania pola powierzchni.

interface Shape {
  getArea(): number;
  getCircut(): number;
}

class Rectangle implements Shape {
  private _a: number;
  private _b: number;

  constructor(a: number, b: number) {
    this._a = a;
    this._b = b;
  }

  getArea(): number {
    return this._a * this._b;
  }
  getCircut(): number {
    return this._a * 2 + this._b * 2;
  }
}

class Circle implements Shape {
  private _r: number;

  constructor(r: number) {
    this._r = r;
  }

  getArea(): number {
    return Math.PI * this._r * this._r;
  }
  getCircut(): number {
    return Math.PI * 2 * this._r;
  }
}

const shape = new Rectangle(3, 4);
console.log(shape.getArea() == 12);
console.log(shape.getCircut() == 14);

const circe = new Circle(5);
console.log(circe.getArea());
console.log(circe.getCircut());

// zd ram machine

interface Memory {
  get(index: number): number;
  set(index: number, value: number): void;
  dump(): (number | null)[];
}

class ArrayMemory implements Memory {
  private _arr: (number | undefined)[];
  private _size: number;

  constructor(size: number) {
    this._size = size;
    this._arr = new Array<number | undefined>(this._size).fill(undefined);
  }

  get(index: number): number {
    return this._arr[index] || 0;
  }

  set(index: number, value: number): void {
    this._arr[index] = value;
  }

  dump(): (number | null)[] {
    const lst: (number | null)[] = [];
    this._arr.forEach((element) => lst.push(element || null));
    return lst;
  }

  get size(): number {
    return this._size;
  }

  get arr(): (number | undefined)[] {
    return this._arr;
  }
}

let mem = new ArrayMemory(5);
console.log(mem.arr);
for (let i = 0; i < mem.size; i++) {
  mem.set(i, i + 1);
}

let lst = mem.dump();

console.log(lst);

type Add = {
  kind: "add";
  param: [number, number, number];
};

type Load = {
  kind: "load";
  param: [number, number];
};

type Move = {
  kind: "move";
  param: [number, number];
};

type Sub = {
  kind: "sub";
  param: [number, number, number];
};

type Instruction = Add | Load | Sub | Move;

interface Machinable {
  step(): void;
  preprocess(): void;
  dump(): (number | null)[];
}

class RAMMachine implements Machinable {
  private _memory: Memory;
  private _instructions: Instruction[];
  private _sizeOfInstructions: number;

  constructor(memory: Memory, newInstructions: Instruction[]) {
    this._memory = memory;
    this._instructions = newInstructions.reverse(); // zeby robic pop
    this._sizeOfInstructions = newInstructions.length;
  }

  preprocess(): void {
    for (let i = 0; i < this._sizeOfInstructions; i++) {
      this.step();
    }
  }

  step(): void {
    const curr = this._instructions.pop();
    const params = curr?.param;
    switch (curr?.kind) {
      /*
       * Add(d,a1,a2)
       */
      case "add":
        if (this.areAddressesCorrect(params as [number, number, number])) {
          const [d, a1, a2] = params as [number, number, number];
          this.makeOperation((a1, a2) => a1 + a2, [d, a1, a2]);
        }
        break;

      case "sub":
        if (this.areAddressesCorrect(params as [number, number, number])) {
          const [d, a1, a2] = params as [number, number, number];
          this.makeOperation((a1, a2) => a1 - a2, [d, a1, a2]);
        }
        break;

      case "move":
        if (this.areAddressesCorrect(params as [number, number])) {
          const [d, a] = params as [number, number];
          this._memory.set(d, this._memory.get(a));
        }
        break;

      case "load":
        if (this.isCorrect(params?.[0] as number)) {
          const [d, v] = params as [number, number];
          this._memory.set(d, v);
        }
        break;

      default:
        console.log("Undefined type of instruction");
        break;
    }
  }

  dump(): (number | null)[] {
    return this._memory.dump();
  }

  private isCorrect(address: number): boolean {
    return address < mem.size && address >= 0;
  }

  private areAddressesCorrect(addresses: number[]): boolean {
    addresses.forEach((element) => {
      if (!this.isCorrect(element)) return false;
    });
    return true;
  }

  private makeOperation(
    f: (firstAgr: number, secArg: number) => number,
    addresses: number[]
  ): void {
    let [d, a1, a2] = addresses;
    if (
      typeof d !== "undefined" &&
      typeof a1 !== "undefined" &&
      typeof a2 !== "undefined"
    )
      this._memory.set(d, f(this._memory.get(a1), this._memory.get(a2)));
  }
}

const lstins: Instruction[] = [
  { kind: "load", param: [0, 1] },
  { kind: "load", param: [1, 5] },
  { kind: "load", param: [2, 2] },
  { kind: "add", param: [3, 1, 2] },
  { kind: "move", param: [4, 0] },
];

const ram = new RAMMachine(new ArrayMemory(10), lstins);
console.log(ram.dump());
ram.preprocess();
console.log(ram.dump());
