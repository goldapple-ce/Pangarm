export default function DateRangePicker({ setDate, date, label }) {
  return (
    <div className="flex flex-col space-y-4">
      <input
        type="date"
        id="end-date"
        value={date}
        onChange={(e) => {
          setDate(label, e.target.value);
        }}
        className="border-gray-300 focus:ring-navy-1 mt-1 block w-full rounded-md border bg-white px-2 py-1.5 shadow-sm focus:border-navy focus:outline-none sm:text-sm"
      />
    </div>
  );
}
